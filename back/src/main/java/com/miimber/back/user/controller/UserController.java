package com.miimber.back.user.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.miimber.back.core.helper.Helper;
import com.miimber.back.core.helper.MailJetService;
import com.miimber.back.organization.dto.organization.OrganizationAndMemberReadResponseDTO;
import com.miimber.back.organization.dto.organization.OrganizationCreateReadUpdateResponseDTO;
import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.Organization;
import com.miimber.back.organization.service.MemberService;
import com.miimber.back.organization.service.OrganizationService;
import com.miimber.back.session.model.Session;
import com.miimber.back.session.service.SessionService;
import com.miimber.back.user.dto.UserEmailUpdateRequestDTO;
import com.miimber.back.user.dto.UserPasswordUpdateRequestDTO;
import com.miimber.back.user.dto.UserSessionReadResponseDTO;
import com.miimber.back.user.dto.UserUpdateRequestDTO;
import com.miimber.back.user.dto.UserValideEmailRequestDTO;
import com.miimber.back.user.model.User;
import com.miimber.back.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Value("${front.url}")
	private String frontUrl;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private MailJetService mailJetService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private SessionService sessionService;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private Helper helper;
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<?> readMe() throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(convertToDto(helper.getUserToken(currentUser)));
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequestDTO userDto, @PathVariable Long id) {
		User user = convertToEntity(userDto, id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
        User tokenUser = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (user.getId() != tokenUser.getId()) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(convertToDto(userService.update(user)));
	}
	
	@RequestMapping(value = "/user/{id}/email", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserEmail(@RequestBody UserEmailUpdateRequestDTO userDto, @PathVariable Long id) throws Exception  {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (user.getId() != id) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		String token = UUID.randomUUID().toString();
		user.setTokenChangeEmail(token);
		user.setNewEmail(userDto.getEmail());
		userService.update(user);
		String link = frontUrl + "/change-email?id="+user.getId()+"&token="+token;
		mailJetService.sendEmailChangeEmail(user.getEmail(), user.getFirstName() + " "+ user.getLastName(), link);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}/email", method = RequestMethod.POST)
	public ResponseEntity<?> validateUserEmail(@RequestBody UserValideEmailRequestDTO userDto, @PathVariable Long id) throws Exception {
		User user = userService.get(id);
		if (!user.getTokenChangeEmail().equals(userDto.getToken())) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		user.setEmail(user.getNewEmail());
		userService.update(user);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}/password", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePasswordUser(@RequestBody UserPasswordUpdateRequestDTO userDTO, @PathVariable Long id) throws Exception  {
        User tokenUser = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (id != tokenUser.getId()) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		if (!bcryptEncoder.matches(userDTO.getOldPassword(), tokenUser.getPassword())) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		userService.updatePasswordUser(tokenUser, userDTO.getNewPassword());
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/user/email/{email}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> readNameExit(@PathVariable String email, @PathVariable Long id) throws Exception {
		User user = userService.getUserByEmail(email);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Member member = memberService.getMemberByOrganizationIdAndByUser(id, user);
		if (member != null) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		return ResponseEntity.ok(convertToDto(user));
	}

	@RequestMapping(value = "/user/organization", method = RequestMethod.GET)
	public ResponseEntity<?> readUserOrganization() throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        List<OrganizationAndMemberReadResponseDTO> responseOrganization = new ArrayList<OrganizationAndMemberReadResponseDTO>();
        for (Organization organization : organizationService.getOrganizationByUser(user)) 
        { 
        	Member member = memberService.getMemberByOrganizationAndByUser(organization, user);
        	responseOrganization.add(new OrganizationAndMemberReadResponseDTO(organization, member));
        }
		return ResponseEntity.ok(responseOrganization);
	}
	
	@RequestMapping(value = "/user/session/", method = RequestMethod.GET)
	public ResponseEntity<?> readUserSessions(@RequestParam String minDate, @RequestParam String maxDate) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        List<UserSessionReadResponseDTO> sessions = new ArrayList<UserSessionReadResponseDTO>();
        for (Session session : sessionService.getSessionByUserAndDate(user, OffsetDateTime.parse(minDate), OffsetDateTime.parse(maxDate)))
        {
        	sessions.add(SessionToUserSessionDTO(session));
        }
        return ResponseEntity.ok(sessions);
	}
	
	private UserUpdateRequestDTO convertToDto(User user) {
		UserUpdateRequestDTO userDto = modelMapper.map(user, UserUpdateRequestDTO.class);
		return userDto;
	}
	
	private User convertToEntity(UserUpdateRequestDTO userDto, long id) {
		User user = userService.get(id);
		if (user == null) {
			return null;
		}
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		return user;
	}

	private OrganizationCreateReadUpdateResponseDTO OrganizationToDTO(Organization organization) {
		return new OrganizationCreateReadUpdateResponseDTO(
        				organization.getId(),
        				organization.getName()
        		);
	}
	
	private UserSessionReadResponseDTO SessionToUserSessionDTO(Session session) {
		return new UserSessionReadResponseDTO(session);
	}
}
