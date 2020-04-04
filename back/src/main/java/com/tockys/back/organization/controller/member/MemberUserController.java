package com.tockys.back.organization.controller.member;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.core.helper.Helper;
import com.tockys.back.core.helper.StripeService;
import com.tockys.back.organization.dto.member.MemberAndUserCreateRequestDTO;
import com.tockys.back.organization.dto.member.MemberCreateResponseDTO;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.organization.service.MemberService;
import com.tockys.back.organization.service.OrganizationService;
import com.tockys.back.user.model.User;
import com.tockys.back.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberUserController {

	@Autowired
	private Helper helper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private StripeService stripeService;
	
	@RequestMapping(value = "/member/user", method = RequestMethod.POST)
	public ResponseEntity<?> createMemberAndUser(@RequestBody MemberAndUserCreateRequestDTO memberByOrganizationRequestDto) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member memberUser = memberService.getMemberByOrganizationIdAndByUser(memberByOrganizationRequestDto.getIdOrganization(), user);
        if (memberUser == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        if (memberUser.getType() == RoleEnum.MEMBER || memberUser.getType() == RoleEnum.INSTRUCTOR) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        Organization organization = organizationService.get(memberByOrganizationRequestDto.getIdOrganization());
        if (organization == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User newUser = new User();
        newUser.setEmail(memberByOrganizationRequestDto.getEmail());
        newUser.setFirstName(memberByOrganizationRequestDto.getFirstName());
        newUser.setLastName(memberByOrganizationRequestDto.getLastName());
        newUser.setToken(UUID.randomUUID().toString());

        newUser = userService.create(newUser);
        
        Member newMember = new Member();
        newMember.setUser(newUser);
        newMember.setType(memberByOrganizationRequestDto.getRole());
        newMember.setOrganization(organization);
        
        stripeService.addOneMemberSubscription(organization.getStripe());
        
        return ResponseEntity.ok(new MemberCreateResponseDTO(memberService.create(newMember)));
	}
}
