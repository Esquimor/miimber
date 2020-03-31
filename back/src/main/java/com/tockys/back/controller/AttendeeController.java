package com.tockys.back.controller;

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

import com.tockys.back.dto.AttendeeCreateDTO;
import com.tockys.back.dto.AttendeeCreateResponseDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.model.Attendee;
import com.tockys.back.model.Member;
import com.tockys.back.model.Session;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RoleEnum;
import com.tockys.back.service.AttendeeService;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.SessionService;
import com.tockys.back.service.UserService;

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
	public ResponseEntity<?> createAttendee(@RequestBody AttendeeCreateDTO attendeeDTO) throws Exception {
		Session session = sessionService.getSessionById(attendeeDTO.getSessionId());
		
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
        
        User userChecked = userService.getUserById(attendeeDTO.getUserId());
        
        if (userChecked == null) {
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Attendee attendee = new Attendee();
        attendee.setSession(session);
        attendee.setUser(userChecked);
        attendee.setDateCheck(attendeeDTO.getDateCheck());
        
        return ResponseEntity.ok(new AttendeeCreateResponseDTO(attendeeService.createAttendee(attendee)));
	}
}
