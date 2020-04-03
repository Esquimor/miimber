package com.tockys.back.session.controller;

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

import com.tockys.back.core.helper.Helper;
import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.service.MemberService;
import com.tockys.back.session.dto.SessionCreateDTO;
import com.tockys.back.session.dto.SessionDTO;
import com.tockys.back.session.dto.SessionEditDTO;
import com.tockys.back.session.dto.SessionReadDTO;
import com.tockys.back.session.model.Session;
import com.tockys.back.session.model.TypeSession;
import com.tockys.back.session.service.SessionService;
import com.tockys.back.session.service.TypeSessionService;
import com.tockys.back.user.model.User;

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
	
	@RequestMapping(value = "/session/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> readSession(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Session session = sessionService.getSessionById(id);
        
        if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Member member = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user);

		return ResponseEntity.ok(SessionAndMemberToSessionReadDTO(session, member, user.getId()));
	}
	
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
        switch (sessionDto.getPeriodicity()) {
        	case ONCE: {
                listSession.add(sessionService.createSession(
                		createSessionBySessionDtoAndDate(sessionDto, sessionDto.getStart(), typeSession, memberUser.getOrganization())
                		));
        		break;
        	}
        	case EVERYDAY: {
                OffsetDateTime endDate = sessionDto.getEndDate().plusDays(2);
                cursor = cursor.plusDays(1);
        		while(cursor.isBefore(endDate)) {
                    listSession.add(sessionService.createSession(
                    		createSessionBySessionDtoAndDate(sessionDto, cursor, typeSession, memberUser.getOrganization())
                    		));
                    cursor = cursor.plusDays(1);
        		}
        		break;
        	}
        	case BY_WEEK: {
                OffsetDateTime endDate = sessionDto.getEndDate().plusDays(2);
                cursor = cursor.plusDays(1);
        		while(cursor.isBefore(endDate)) {
        			if (sessionDto.getDays().contains(cursor.getDayOfWeek().getValue())) {
                        listSession.add(sessionService.createSession(
                        		createSessionBySessionDtoAndDate(sessionDto, cursor, typeSession, memberUser.getOrganization())
                        		));
        			}
        			if (cursor.getDayOfWeek() == DayOfWeek.SUNDAY) {
        				cursor = cursor.plusWeeks(sessionDto.getRepeat() - 1);
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
	public ResponseEntity<?> updateSession(@RequestBody SessionEditDTO sessionDto, @PathVariable Long id) throws Exception {
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
        session.setTitle(sessionDto.getTitle());
        session.setDescription(sessionDto.getDescription());
        session.setStart(sessionDto.getStart());
        session.setEnd(sessionDto.getEnd());
        session.setTypeSession(typeSession);
        session.setLimit(sessionDto.getLimit());

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
	
	private Session createSessionBySessionDtoAndDate(SessionCreateDTO sessionDto, OffsetDateTime date, TypeSession typeSession, Organization organization) {
		Session session = new Session();
        session.setTitle(sessionDto.getTitle());
        session.setDescription(sessionDto.getDescription());
        session.setStart(mixDateAndTime(date, sessionDto.getStart()));
        session.setEnd(mixDateAndTime(date, sessionDto.getEnd()));
        session.setTypeSession(typeSession);
        session.setOrganization(organization);
        session.setLimit(sessionDto.getLimit());
        return session;
	}
	
	private OffsetDateTime mixDateAndTime(OffsetDateTime date, OffsetDateTime time) {
		return OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond(), time.getNano(), time.getOffset());
	}
	
	private SessionReadDTO SessionAndMemberToSessionReadDTO(Session session, Member member, Long userId) {
		return new SessionReadDTO(session, member, userId);
	}
}
