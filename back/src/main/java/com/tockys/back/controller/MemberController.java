package com.tockys.back.controller;

import java.util.UUID;

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

import com.tockys.back.dto.MemberByOrganizationRequestDTO;
import com.tockys.back.dto.MemberCreateResponseDTO;
import com.tockys.back.dto.MemberDTO;
import com.tockys.back.dto.MemberResponseDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RoleEnum;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.OrganizationService;
import com.tockys.back.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRole(@RequestBody MemberDTO memberDto, @PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member member = memberService.getMember(id);
        if (member == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Member memberUser = memberService.getMemberByOrganizationAndByUser(member.getOrganization(), user);
        if (memberUser.getType() == RoleEnum.MEMBER) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (memberUser.getType() != RoleEnum.OWNER && memberDto.getRole() == RoleEnum.OWNER) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (member.getType() == RoleEnum.OWNER && memberUser.getType() != RoleEnum.OWNER) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        member.setType(memberDto.getRole());
        return ResponseEntity.ok(convertMemberToMemberResponseDTO(memberService.updateMember(member)));
	}
	
	@RequestMapping(value = "member/me/{id}", method = RequestMethod.GET) 
	public ResponseEntity<?> memberMe(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Member member = memberService.getMemberByOrganizationIdAndByUser(id, user);
        return ResponseEntity.ok(convertMemberToMemberResponseDTO(member));
	}
	
	@RequestMapping(value = "/member/user", method = RequestMethod.POST)
	public ResponseEntity<?> createMemberAndUser(@RequestBody MemberByOrganizationRequestDTO memberByOrganizationRequestDto) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member memberUser = memberService.getMemberByOrganizationIdAndByUser(memberByOrganizationRequestDto.getIdOrganization(), user);
        if (memberUser == null ) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        if (memberUser.getType() == RoleEnum.MEMBER || memberUser.getType() == RoleEnum.INSTRUCTOR) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        Organization organization = organizationService.getOrganization(memberByOrganizationRequestDto.getIdOrganization());
        if (organization == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User newUser = new User();
        newUser.setEmail(memberByOrganizationRequestDto.getEmail());
        newUser.setFirstName(memberByOrganizationRequestDto.getFirstName());
        newUser.setLastName(memberByOrganizationRequestDto.getLastName());
        newUser.setToken(UUID.randomUUID().toString());

        newUser = userService.createUser(newUser);
        
        Member newMember = new Member();
        newMember.setUser(newUser);
        newMember.setType(memberByOrganizationRequestDto.getRole());
        newMember.setOrganization(organization);
        
        return ResponseEntity.ok(convertMemberToMemberCreateResponseDTO(memberService.createMember(newMember)));
	}
	
	
	private MemberResponseDTO convertMemberToMemberResponseDTO(Member member) {
		return new MemberResponseDTO(member);
	}
	
	private MemberCreateResponseDTO convertMemberToMemberCreateResponseDTO(Member member) {
		return new MemberCreateResponseDTO(member);
	}
}
