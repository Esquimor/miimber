package com.tockys.back.controller;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.tockys.back.enumItem.PeriodicityEnum;
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
        
        List<Session> listSession = new ArrayList<Session>();
        OffsetDateTime cursor =  sessionDto.getStartDate();
        OffsetDateTime endDate = sessionDto.getEndDate().plusDays(1);
        switch (sessionDto.getPeriodicity()) {
        	case ONCE: {
                Session session = new Session();
                session.setTitle(sessionDto.getTitle());
                session.setDescription(sessionDto.getDescription());
                session.setStart(sessionDto.getStart());
                session.setEnd(sessionDto.getEnd());
                session.setTypeSession(typeSession);
                session.setOrganization(memberUser.getOrganization());
                listSession.add(sessionService.createSession(session));
        	}
        	case EVERYDAY: {
        		while(cursor.isBefore(endDate)) {
        			Session session = new Session();
                    session.setTitle(sessionDto.getTitle());
                    session.setDescription(sessionDto.getDescription());
                    session.setStart(mixDateAndTime(cursor, sessionDto.getStart()));
                    session.setEnd(mixDateAndTime(cursor, sessionDto.getEnd()));
                    session.setTypeSession(typeSession);
                    session.setOrganization(memberUser.getOrganization());
                    listSession.add(sessionService.createSession(session));
                    cursor = cursor.plusDays(1);
        		}
        		break;
        	}
        	case BY_WEEK: {
        		while(cursor.isBefore(endDate)) {
        			if (sessionDto.getDays().contains(cursor.getDayOfWeek().getValue())) {
            			Session session = new Session();
                        session.setTitle(sessionDto.getTitle());
                        session.setDescription(sessionDto.getDescription());
                        session.setStart(mixDateAndTime(cursor, sessionDto.getStart()));
                        session.setEnd(mixDateAndTime(cursor, sessionDto.getEnd()));
                        session.setTypeSession(typeSession);
                        session.setOrganization(memberUser.getOrganization());
                        listSession.add(sessionService.createSession(session));
        			}
        			if (cursor.getDayOfWeek() == DayOfWeek.SUNDAY) {
        				cursor.plusWeeks(sessionDto.getRepeat() - 1);
        			}
                    cursor = cursor.plusDays(1);
        		}
        		break;
        	}
        	default : {
        		return new ResponseEntity(HttpStatus.CONFLICT);
        	}
        }

        
		return ResponseEntity.ok(listSessionToListSessionDto(listSession));
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
	
	private List<SessionDTO> listSessionToListSessionDto(List<Session> listSession) {
		List<SessionDTO> listSessionDto = new ArrayList<SessionDTO>();
		for(Session session: listSession) {
			listSessionDto.add(SessionToSessionDto(session));
		}
		return listSessionDto;
	}
	
	private SessionDTO SessionToSessionDto(Session session) {
		return new SessionDTO(session);
	}
	
	private OffsetDateTime mixDateAndTime(OffsetDateTime date, OffsetDateTime time) {
		return OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth() + 1, time.getHour(), time.getMinute(), time.getSecond(), time.getNano(), time.getOffset());
	}
}
