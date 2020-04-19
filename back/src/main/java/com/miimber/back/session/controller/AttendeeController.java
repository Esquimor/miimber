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
import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.enums.RoleEnum;
import com.miimber.back.organization.service.MemberService;
import com.miimber.back.session.dto.attendee.AttendeeCreateRequestDTO;
import com.miimber.back.session.dto.attendee.AttendeeCreateResponseDTO;
import com.miimber.back.session.model.Attendee;
import com.miimber.back.session.model.Session;
import com.miimber.back.session.service.AttendeeService;
import com.miimber.back.session.service.SessionService;
import com.miimber.back.user.model.User;
import com.miimber.back.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttendeeController {
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private AttendeeService attendeeService;

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/attendee/", method = RequestMethod.POST)
	public ResponseEntity<?> createAttendee(@RequestBody AttendeeCreateRequestDTO attendeeDTO) throws Exception {
		Session session = sessionService.get(attendeeDTO.getSessionId());
		
		if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member member = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user);
        
        if (member == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
        if (member.getType() == RoleEnum.MEMBER || member.getType() == RoleEnum.OFFICE) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        User userChecked = userService.get(attendeeDTO.getUserId());
        
        if (userChecked == null) {
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Attendee attendee = new Attendee();
        attendee.setSession(session);
        attendee.setUser(userChecked);
        attendee.setDateCheck(attendeeDTO.getDateCheck());
        
        return ResponseEntity.ok(new AttendeeCreateResponseDTO(attendeeService.create(attendee)));
	}
	
	@RequestMapping(value = "/attendee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAttendee(@PathVariable Long id) throws Exception {
		Attendee attendee = attendeeService.get(id);
		
		if (attendee == null) {
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Member member = memberService.getMemberByOrganizationAndByUser(attendee.getSession().getOrganization(), user);
        
        if (member == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
        if (member.getType() == RoleEnum.MEMBER || member.getType() == RoleEnum.OFFICE) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        attendeeService.delete(attendee);

        return new ResponseEntity(HttpStatus.OK);
	}
}
