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
import com.tockys.back.session.dto.session.SessionCreateRequestDTO;
import com.tockys.back.session.dto.session.SessionEditRequestDTO;
import com.tockys.back.session.dto.session.SessionReadResponseDTO;
import com.tockys.back.session.dto.session.SessionShortReadResponseDTO;
import com.tockys.back.session.dto.session.SessionUsersReadResponseDTO;
import com.tockys.back.session.model.Attendee;
import com.tockys.back.session.model.Registered;
import com.tockys.back.session.model.Session;
import com.tockys.back.session.model.TypeSession;
import com.tockys.back.session.service.SessionService;
import com.tockys.back.session.service.TypeSessionService;
import com.tockys.back.user.dto.TemplateAttendeeDTO;
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
        
        Session session = sessionService.get(id);
        
        if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        Member member = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user);

		return ResponseEntity.ok(SessionAndMemberToSessionReadDTO(session, member, user.getId()));
	}
	
	@RequestMapping(value = "/session/{id}/user/", method = RequestMethod.GET)
	public ResponseEntity<?> readSessionUsers(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Session session = sessionService.get(id);
        
        if (session == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Member memberUser = memberService.getMemberByOrganizationAndByUser(session.getOrganization(), user);
        
        if (!memberUser.canEmergeOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

		List<Member> members = session.getOrganization().getMembers();
		List<Attendee> attendees = session.getAttendees();
		List<Registered> registereds = session.getRegistereds();
        
		List<TemplateAttendeeDTO> users = new ArrayList<TemplateAttendeeDTO>();
		
		if (session.getLimit() == 0) {
	    	users.addAll(getAllMembers(members, attendees, registereds));
	    	users.addAll(getAllAttendeesWithoutAlreadyUsers(attendees, registereds, users));
	    	users.addAll(getAllRegisteredsWithourAlreadyUsers(registereds, users));
		} else {
			users.addAll(getAllRegistered(registereds));
		}
		
		return ResponseEntity.ok(new SessionUsersReadResponseDTO(session, users));
	}

	
	@RequestMapping(value = "/session/", method = RequestMethod.POST)
	public ResponseEntity<?> createSession(@RequestBody SessionCreateRequestDTO sessionDto) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member memberUser = memberService.getMemberByOrganizationIdAndByUser(sessionDto.getOrganizationId(), user);
        if (memberUser == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!memberUser.canEditOrganization()) {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        TypeSession typeSession = typeSessionService.get(sessionDto.getTypeSessionId());
        if (typeSession == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        List<Session> listSession = new ArrayList<Session>();
        OffsetDateTime cursor =  sessionDto.getStartDate();
        switch (sessionDto.getPeriodicity()) {
        	case ONCE: {
                listSession.add(sessionService.create(
                		createSessionBySessionDtoAndDate(sessionDto, sessionDto.getStart(), typeSession, memberUser.getOrganization())
                		));
        		break;
        	}
        	case EVERYDAY: {
                OffsetDateTime endDate = sessionDto.getEndDate().plusDays(2);
                cursor = cursor.plusDays(1);
        		while(cursor.isBefore(endDate)) {
                    listSession.add(sessionService.create(
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
                        listSession.add(sessionService.create(
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
	public ResponseEntity<?> updateSession(@RequestBody SessionEditRequestDTO sessionDto, @PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Session session = sessionService.get(id);
        
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
        
        TypeSession typeSession = typeSessionService.get(sessionDto.getTypeSessionId());
        if (typeSession == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        session.setTitle(sessionDto.getTitle());
        session.setDescription(sessionDto.getDescription());
        session.setStart(sessionDto.getStart());
        session.setEnd(sessionDto.getEnd());
        session.setTypeSession(typeSession);
        session.setLimit(sessionDto.getLimit());

		return ResponseEntity.ok(new SessionShortReadResponseDTO(sessionService.update(session)));
	}
	
	@RequestMapping(value= "/session/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSession(@PathVariable Long id) throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Session session = sessionService.get(id);
        
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
        
        sessionService.delete(session);

        return new ResponseEntity(HttpStatus.OK);
	}
	
	private List<SessionShortReadResponseDTO> listSessionToListSessionDto(List<Session> listSession) {
		List<SessionShortReadResponseDTO> listSessionDto = new ArrayList<SessionShortReadResponseDTO>();
		for(Session session: listSession) {
			listSessionDto.add(new SessionShortReadResponseDTO(session));
		}
		return listSessionDto;
	}
	
	
	private Session createSessionBySessionDtoAndDate(SessionCreateRequestDTO sessionDto, OffsetDateTime date, TypeSession typeSession, Organization organization) {
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
	
	private SessionReadResponseDTO SessionAndMemberToSessionReadDTO(Session session, Member member, Long userId) {
		return new SessionReadResponseDTO(session, member, userId);
	}

	// List all organization members
	private List<TemplateAttendeeDTO> getAllMembers(List<Member> members, List<Attendee> attendees, List<Registered> registereds) {
		List<TemplateAttendeeDTO> users = new ArrayList<TemplateAttendeeDTO>();
		for(Member member: members) {
			Long attendeId = 0L;
			boolean isRegistered = false;
			// Look if memberOrganization is present
			for(Attendee attendee: attendees) {
				if (attendee.getUser().getId() == member.getUser().getId()) {
					attendeId = attendee.getId();
					break;
				}
			}
			for(Registered registered: registereds) {
				if (member.getUser().getId() == registered.getUser().getId()) {
					isRegistered = true;
					break;
				}
			}
			// add it
			users.add(new TemplateAttendeeDTO(member, attendeId, isRegistered));
		}
		return users;
	}

	// List all users outside organization
	private List<TemplateAttendeeDTO> getAllAttendeesWithoutAlreadyUsers(List<Attendee> attendees, List<Registered> registereds, List<TemplateAttendeeDTO> alreadyUsers) {
		List<TemplateAttendeeDTO> users = new ArrayList<TemplateAttendeeDTO>();
		for(Attendee attendee: attendees) {
			User userAttendee = attendee.getUser();
			boolean alreadyTaken = false;
			// Look if attendee is already a member
			for(TemplateAttendeeDTO user: alreadyUsers) {
				if (user.getId() == userAttendee.getId()) {
					alreadyTaken = true;
					break;
				}
			}
			// If attendee is not a member add it
			if (alreadyTaken == false) {
				boolean isRegistered = false;
				for(Registered registered: registereds) {
					if (attendee.getUser().getId() == registered.getUser().getId()) {
						isRegistered = true;
						break;
					}
				}
				users.add(new TemplateAttendeeDTO(userAttendee, attendee.getId(), isRegistered));
			}
		}
		return users;
	}
	
	private List<TemplateAttendeeDTO> getAllRegisteredsWithourAlreadyUsers(List<Registered> registereds, List<TemplateAttendeeDTO> alreadyUsers) {
		List<TemplateAttendeeDTO> users = new ArrayList<TemplateAttendeeDTO>();
		for(Registered registered: registereds) {
			// Look if attendee is already a member
			User userRegistered = registered.getUser();
			boolean alreadyTaken = false;
			for(TemplateAttendeeDTO user: alreadyUsers) {
				if (user.getId() == userRegistered.getId()) {
					alreadyTaken = true;
					break;
				}
			}
			if (alreadyTaken == false) {
				users.add(new TemplateAttendeeDTO(userRegistered, 0L, false));
			}
		}
		return users;
	}
	
	private List<TemplateAttendeeDTO> getAllRegistered(List<Registered> registereds) {
		List<TemplateAttendeeDTO> users = new ArrayList<TemplateAttendeeDTO>();
		for(Registered registered: registereds) {
			// Look if attendee is already a member
			User userRegistered = registered.getUser();
			users.add(new TemplateAttendeeDTO(userRegistered, 0L, false));
		}
		return users;
	}
}
