package com.tockys.back.organization.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.core.helper.Helper;
import com.tockys.back.organization.dto.member.MemberReadUpdateResponseDTO;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.service.MemberService;
import com.tockys.back.user.model.User;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MeController {
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private MemberService memberService;

	
	@RequestMapping(value = "member/me/{id}", method = RequestMethod.GET) 
	public ResponseEntity<?> readMemberMe(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Member member = memberService.getMemberByOrganizationIdAndByUser(id, user);
        return ResponseEntity.ok(new MemberReadUpdateResponseDTO(member));
	}
}
