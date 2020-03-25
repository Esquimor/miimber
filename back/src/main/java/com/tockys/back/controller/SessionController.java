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

import com.tockys.back.dto.SessionCreateDTO;
import com.tockys.back.dto.SessionDTO;
import com.tockys.back.dto.SessionEditDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.model.Member;
import com.tockys.back.model.Session;
import com.tockys.back.model.TypeSession;
import com.tockys.back.model.User;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.SessionService;
import com.tockys.back.service.TypeSessionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionController {

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private TypeSessionService typeSessionService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Helper helper;
	
	@RequestMapping(value = "/session/", method = RequestMethod.POST)
	public ResponseEntity<?> createSession(@RequestBody SessionCreateDTO sessionDto) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member memberUser = memberService.getMemberByOrganizationIdAndByUser(sessionDto.getOrganizationId(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        TypeSession typeSession = typeSessionService.getTypeSessionById(sessionDto.getTypeSessionId());
        if (typeSession == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Session session = new Session();
        session.setStart(sessionDto.getStart());
        session.setEnd(sessionDto.getEnd());
        session.setTypeSession(typeSession);
        session.setOrganization(memberUser.getOrganization());
        
		return ResponseEntity.ok(SessionToSessionDto(sessionService.createSession(session)));
	}
	
	@RequestMapping(value = "/session/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editSession(@RequestBody SessionEditDTO sessionDto, @PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Session session = sessionService.getSessionById(id);
        
        if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Member memberUser = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        TypeSession typeSession = typeSessionService.getTypeSessionById(sessionDto.getTypeSessionId());
        if (typeSession == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        session.setStart(sessionDto.getStart());
        session.setEnd(sessionDto.getEnd());
        session.setTypeSession(typeSession);

		return ResponseEntity.ok(SessionToSessionDto(sessionService.editSession(session)));
	}
	
	@RequestMapping(value= "/session/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSession(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Session session = sessionService.getSessionById(id);
        
        if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Member memberUser = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        sessionService.deleteSession(session);

        return new ResponseEntity(HttpStatus.OK);
	}
	
	private SessionDTO SessionToSessionDto(Session session) {
		return new SessionDTO(session);
	}
}
