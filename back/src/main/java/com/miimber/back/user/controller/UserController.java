package com.tockys.back.user.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.tockys.back.core.helper.Helper;
import com.tockys.back.organization.dto.organization.OrganizationAndMemberReadResponseDTO;
import com.tockys.back.organization.dto.organization.OrganizationCreateReadUpdateResponseDTO;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.service.MemberService;
import com.tockys.back.organization.service.OrganizationService;
import com.tockys.back.session.model.Session;
import com.tockys.back.session.service.SessionService;
import com.tockys.back.user.dto.UserUpdateRequestDTO;
import com.tockys.back.user.dto.UserPasswordUpdateRequestDTO;
import com.tockys.back.user.dto.UserSessionReadResponseDTO;
import com.tockys.back.user.model.User;
import com.tockys.back.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
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

	@RequestMapping(value = "/user/{id}/password", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePasswordUser(@RequestBody UserPasswordUpdateRequestDTO userDTO, @PathVariable Long id) {
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
