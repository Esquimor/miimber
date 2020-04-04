package com.tockys.back.organization.controller.organization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.organization.dto.organization.OrganizationMembersReadResponseDTO;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.service.MemberService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/organization/{id}/member/", method = RequestMethod.GET)
	public ResponseEntity<?> readOrganizationMember(@PathVariable Long id) throws Exception {
		List<OrganizationMembersReadResponseDTO> listMembers = new ArrayList<OrganizationMembersReadResponseDTO>();
		
		for (Member member: memberService.getMemberByOrganizationId(id)) {
			listMembers.add(new OrganizationMembersReadResponseDTO(member));
		}
		return ResponseEntity.ok(listMembers);
	}
}
