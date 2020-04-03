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

import com.tockys.back.dto.RegisteredCreateDTO;
import com.tockys.back.dto.RegisteredCreateResponseDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.model.Registered;
import com.tockys.back.model.Session;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RegisteredEnum;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.RegisteredService;
import com.tockys.back.service.SessionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegisteredController {

	@Autowired
	private RegisteredService registeredService;

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Helper helper;

	@RequestMapping(value= "/registered/", method = RequestMethod.POST)
	public ResponseEntity<?> createRegistered(@RequestBody RegisteredCreateDTO registeredDto) throws Exception {
		Session session = sessionService.getSessionById(registeredDto.getSessionId());
		
		if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
    	Registered registered = new Registered();
    	registered.setSession(session);
    	registered.setUser(user);
    	registered.setDateRegistered(registeredDto.getDateRegistered());
    	registered = registeredService.createRegistered(registered);
    	
    	long nbRegistered = registeredService.countRegisteredBySession(session);
		RegisteredEnum status = RegisteredEnum.TAKEN;
		if (session.getLimit() != 0) {
			status = session.getLimit() > nbRegistered ? RegisteredEnum.TAKEN : RegisteredEnum.WAITING;
		}
    	
    	boolean isMember = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user) != null;
    	
    	return ResponseEntity.ok(new RegisteredCreateResponseDTO(registered, isMember, status));
	}
	
	@RequestMapping(value = "/registered/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRegistered(@PathVariable long id) throws Exception {
		Registered registered = registeredService.getRegisteredById(id);
		
		if (registered == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        if (user.getId() != registered.getUser().getId()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        registeredService.removeRegistered(registered);

        return new ResponseEntity(HttpStatus.OK);
	}
}
