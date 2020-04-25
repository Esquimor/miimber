package com.miimber.back.session.controller;

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

import com.miimber.back.core.helper.Helper;
import com.miimber.back.organization.service.MemberService;
import com.miimber.back.session.dto.registered.RegisteredSessionCreateRequestDTO;
import com.miimber.back.session.dto.registered.RegisteredSessionCreateResponseDTO;
import com.miimber.back.session.model.RegisteredSession;
import com.miimber.back.session.model.Session;
import com.miimber.back.session.model.enums.RegisteredEnum;
import com.miimber.back.session.service.RegisteredSessionService;
import com.miimber.back.session.service.SessionService;
import com.miimber.back.user.model.User;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegisteredSessionController {

	@Autowired
	private RegisteredSessionService registeredService;

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Helper helper;

	@RequestMapping(value= "/registered/", method = RequestMethod.POST)
	public ResponseEntity<?> createRegistered(@RequestBody RegisteredSessionCreateRequestDTO registeredDto) throws Exception {
		Session session = sessionService.get(registeredDto.getSessionId());
		
		if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
    	RegisteredSession registered = new RegisteredSession();
    	registered.setSession(session);
    	registered.setUser(user);
    	registered.setDateRegistered(registeredDto.getDateRegistered());
    	registered = registeredService.create(registered);
    	
		RegisteredEnum status = RegisteredEnum.TAKEN;
		if (session.getLimit() != 0) {
	    	long nbRegistered = registeredService.countRegisteredBySession(session);
			status = session.getLimit() > nbRegistered ? RegisteredEnum.TAKEN : RegisteredEnum.WAITING;
		}
    	
    	boolean isMember = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user) != null;
    	
    	return ResponseEntity.ok(new RegisteredSessionCreateResponseDTO(registered, isMember, status));
	}
	
	@RequestMapping(value = "/registered/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRegistered(@PathVariable long id) throws Exception {
		RegisteredSession registered = registeredService.get(id);
		
		if (registered == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        if (user.getId() != registered.getUser().getId()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        registeredService.delete(registered);

        return new ResponseEntity(HttpStatus.OK);
	}
}
