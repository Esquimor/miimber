package com.tockys.back.controller;

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

import com.tockys.back.dto.TypeSessionDTO;
import com.tockys.back.dto.TypeSessionNameDTO;
import com.tockys.back.dto.TypeSessionRequestDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.model.Member;
import com.tockys.back.model.TypeSession;
import com.tockys.back.model.User;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.TypeSessionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TypeSessionController {
	
	@Autowired
	private TypeSessionService typeSessionService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Helper helper;
	

	@RequestMapping(value = "/type-session/", method = RequestMethod.POST)
	public ResponseEntity<?> createTypeSession(@RequestBody TypeSessionRequestDTO typeSessionDto) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member memberUser = memberService.getMemberByOrganizationIdAndByUser(typeSessionDto.getOrganizationId(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        TypeSession typeSession = new TypeSession();
        typeSession.setName(typeSessionDto.getName());
        typeSession.setOrganization(memberUser.getOrganization());

        return ResponseEntity.ok(TypeSessionToDTO(typeSessionService.createTypeSession(typeSession)));
	}
	
	@RequestMapping(value = "/type-session/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editTypeSession(@RequestBody TypeSessionNameDTO typeSessionDto, @PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        TypeSession typeSession = typeSessionService.getTypeSessionById(id);
        if (typeSession == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Member memberUser = memberService.getMemberByOrganizationAndByUser(typeSession.getOrganization(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        typeSession.setName(typeSessionDto.getName());

        return ResponseEntity.ok(TypeSessionToDTO(typeSessionService.editTypeSession(typeSession)));
	}
	
	@RequestMapping(value = "/type-session/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTypeSession(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        TypeSession typeSession = typeSessionService.getTypeSessionById(id);
        if (typeSession == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Member memberUser = memberService.getMemberByOrganizationAndByUser(typeSession.getOrganization(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        typeSessionService.deleteTypeSession(typeSession);
        return new ResponseEntity(HttpStatus.OK);
	}

	
	private TypeSessionDTO TypeSessionToDTO(TypeSession typeSession) {
		return new TypeSessionDTO(
					typeSession.getId(),
					typeSession.getName()
				);
	}
}
