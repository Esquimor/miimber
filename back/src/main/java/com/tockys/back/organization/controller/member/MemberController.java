package com.tockys.back.organization.controller.member;

import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.core.helper.Helper;
import com.tockys.back.core.helper.StripeService;
import com.tockys.back.organization.dto.member.MemberCreateResponseDTO;
import com.tockys.back.organization.dto.member.MemberUpdateRequestDTO;
import com.tockys.back.organization.dto.member.MemberCreateRequestDTO;
import com.tockys.back.organization.dto.member.MemberReadUpdateResponseDTO;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.organization.service.MemberService;
import com.tockys.back.organization.service.OrganizationService;
import com.tockys.back.user.model.User;
import com.tockys.back.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
	
	@Autowired
	private Helper helper;

	@Autowired
	private StripeService stripeService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRole(@RequestBody MemberUpdateRequestDTO memberDto, @PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member member = memberService.get(id);
        if (member == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Member memberUser = memberService.getMemberByOrganizationAndByUser(member.getOrganization(), user);
        if (memberUser.getType() == RoleEnum.MEMBER || memberUser.getType() == RoleEnum.INSTRUCTOR) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (memberUser.getType() != RoleEnum.OWNER && memberDto.getRole() == RoleEnum.OWNER) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (member.getType() == RoleEnum.OWNER && memberUser.getType() != RoleEnum.OWNER) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (memberDto.getRole() == RoleEnum.OWNER && member.getId() == memberUser.getId()) {
            Predicate<Member> byType = m -> m.getType() == RoleEnum.OWNER;
            int numberOwners = member.getOrganization().getMembers().stream().filter(byType)
                    .collect(Collectors.toList()).size();
            if (numberOwners == 1) {
            	return new ResponseEntity(HttpStatus.CONFLICT);
            }
        }
        member.setType(memberDto.getRole());
        return ResponseEntity.ok(new MemberReadUpdateResponseDTO(memberService.update(member)));
	}
	
	@RequestMapping(value = "/member/", method = RequestMethod.POST)
	public ResponseEntity<?> createMember(@RequestBody MemberCreateRequestDTO memberDto) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Organization organization = organizationService.get(memberDto.getIdOrganization());
        
        Member memberUser = memberService.getMemberByOrganizationAndByUser(organization, user);
        if (memberUser == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        if (memberUser.getType() == RoleEnum.MEMBER || memberUser.getType() == RoleEnum.INSTRUCTOR) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        User userToMember = userService.get(memberDto.getIdUser());
        if (userToMember == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Member existingMember = memberService.getMemberByOrganizationAndByUser(organization, userToMember);
        if (existingMember != null) {
        	return new ResponseEntity(HttpStatus.CONFLICT);
        }
        
        Member newMember = new Member();
        newMember.setUser(userToMember);
        newMember.setType(memberDto.getRole());
        newMember.setOrganization(organization);
        
        if (organization.getStripe() != null) {
            stripeService.addOneMemberSubscription(organization.getStripe());
        }
        
        return ResponseEntity.ok(new MemberCreateResponseDTO(memberService.create(newMember)));
	}

	@RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMember(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member member = memberService.get(id);
        if (member == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        if (member.getOrganization().getMembers().size() == 1) {
        	return new ResponseEntity(HttpStatus.CONFLICT);
        }
        
        Member memberUser = memberService.getMemberByOrganizationAndByUser(member.getOrganization(), user);
        if (memberUser == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        if (memberUser.getType() == RoleEnum.MEMBER || memberUser.getType() == RoleEnum.INSTRUCTOR) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        if (member.getType() == RoleEnum.OWNER && memberUser.getType() != RoleEnum.OWNER) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        if (member.getOrganization().getStripe() != null) {
            stripeService.removeOneMemberSubscription(member.getOrganization().getStripe());
        }
        
        memberService.delete(member);
        return new ResponseEntity(HttpStatus.OK);
	}
}
