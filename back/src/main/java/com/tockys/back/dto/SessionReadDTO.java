package com.tockys.back.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tockys.back.model.Attendee;
import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.Registered;
import com.tockys.back.model.Session;
import com.tockys.back.model.TypeSession;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RegisteredEnum;
import com.tockys.back.model.enums.RoleEnum;

public class SessionReadDTO {

	private long id;
	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private OrganizationDTO organization;
	private TypeSessionDTO typeSession;
	private MeDTO me;
	private List<UserDTO> users;
	private List<RegisteredDTO> registereds;
	private int limit;
	
	public SessionReadDTO(Session session, Member member, Long userId) {
		this.setId(session.getId());
		this.setTitle(session.getTitle());
		this.setDescription(session.getDescription());
		this.setStart(session.getStart());
		this.setEnd(session.getEnd());
		this.setLimit(session.getLimit());
		this.setOrganization(new OrganizationDTO(session.getOrganization()));
		this.setTypeSession(new TypeSessionDTO(session.getTypeSession()));
		this.setMe(new MeDTO(member, getByUserId(session, userId)));
		if ( member != null && (member.getType() == RoleEnum.INSTRUCTOR ||
			member.getType() == RoleEnum.OFFICE_INSTRUCTOR ||
			member.getType() == RoleEnum.OWNER)) {
			setUsers(session);
		}
		setRegistereds(session);
	}
	
	private Registered getByUserId(Session session, long id) {
		Registered findRegistered = null;
		for(Registered registered: session.getRegistereds()) {
			if (registered.getUser().getId() == id) {
				findRegistered = registered;
				break;
			}
		}
		return findRegistered;
	}
	
	private void setRegistereds(Session session) {
		this.registereds = new ArrayList<RegisteredDTO>();
		List<Member> members = session.getOrganization().getMembers();
		List<Registered> registereds = session.getRegistereds();
		Collections.sort(registereds);
		int limitSession = session.getLimit();
		for(int i = 0; i < registereds.size(); i++) {
			boolean isMember = false;
			for(Member member: members) {
				if (member.getUser().getId() == registereds.get(i).getUser().getId()) {
					isMember = true;
					break;
				}
			}
			RegisteredEnum status = RegisteredEnum.TAKEN;
			if (limitSession != 0) {
				status = limitSession > i ? RegisteredEnum.TAKEN : RegisteredEnum.WAITING;
			}
			this.registereds.add(new RegisteredDTO(registereds.get(i), isMember, status));
		}
	}
	
	private void setUsers(Session session) {
		this.users = new ArrayList<UserDTO>();
		List<Member> members = session.getOrganization().getMembers();
		List<Attendee> attendees = session.getAttendees();
		// List all organization members
		for(Member member: members) {
			Long attendeId = 0L;
			// Look if memberOrganization is present
			for(Attendee attendee: attendees) {
				if (attendee.getUser().getId() == member.getUser().getId()) {
					attendeId = attendee.getId();
					break;
				}
			}
			// add it
			this.users.add(new UserDTO(member, attendeId));
		}
		
		// List all users outside organization
		for(Attendee attendee: attendees) {
			User user = attendee.getUser();
			boolean isMember = false;
			// Look if attendee is already a member
			for(Member memberToTest: members) {
				if (memberToTest.getUser().getId() == user.getId()) {
					isMember = true;
					break;
				}
			}
			// If attendee is not a member add it
			if (isMember == false) {
				this.users.add(new UserDTO(user, attendee.getId()));
			}
		}
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OffsetDateTime getStart() {
		return start;
	}

	public void setStart(OffsetDateTime start) {
		this.start = start;
	}

	public OffsetDateTime getEnd() {
		return end;
	}

	public void setEnd(OffsetDateTime end) {
		this.end = end;
	}

	public OrganizationDTO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}

	public TypeSessionDTO getTypeSession() {
		return typeSession;
	}

	public void setTypeSession(TypeSessionDTO typeSession) {
		this.typeSession = typeSession;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public List<RegisteredDTO> getRegistereds() {
		return registereds;
	}

	public void setRegistereds(List<RegisteredDTO> registereds) {
		this.registereds = registereds;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public MeDTO getMe() {
		return me;
	}

	public void setMe(MeDTO me) {
		this.me = me;
	}

	private class OrganizationDTO {
		
		private long id;
		private String name;
		
		public OrganizationDTO(Organization organization) {
			this.setId(organization.getId());
			this.setName(organization.getName());
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	}
	
	private class TypeSessionDTO {
		
		private long id;
		private String name;
		
		public TypeSessionDTO(TypeSession typeSession) {
			this.setId(typeSession.getId());
			this.setName(typeSession.getName());
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	}
	
	private class MeDTO {
		
		private MemberUserDTO member;
		private MeRegisteredDTO registered;
		
		public MeDTO(Member member, Registered registered) {
			this.member = new MemberUserDTO(member);
			if (registered != null) {
				this.setRegistered(new MeRegisteredDTO(registered));
			}
		}

		public MemberUserDTO getMember() {
			return member;
		}

		public void setMember(MemberUserDTO member) {
			this.member = member;
		}
		
		public MeRegisteredDTO getRegistered() {
			return registered;
		}

		public void setRegistered(MeRegisteredDTO registered) {
			this.registered = registered;
		}

		private class MeRegisteredDTO {
			
			private long id;
			private OffsetDateTime dateRegistered;
			
			public MeRegisteredDTO(Registered registered) {
				this.setId(registered.getId());
				this.setDateRegistered(registered.getDateRegistered());
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public OffsetDateTime getDateRegistered() {
				return dateRegistered;
			}

			public void setDateRegistered(OffsetDateTime dateRegistered) {
				this.dateRegistered = dateRegistered;
			}
		}

		private class MemberUserDTO {
			
			private long id;
			private RoleEnum role;
			
			public MemberUserDTO(Member member) {
				this.setId(member.getId());
				this.setRole(member.getType());
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public RoleEnum getRole() {
				return role;
			}

			public void setRole(RoleEnum role) {
				this.role = role;
			}
		}
	}

	private class UserDTO {
		
		private long id;
		private String firstName;
		private String lastName;
		private String email;
		private MemberDTO member;
		private long attendeeId;
		
		public UserDTO(Member member, long attendeeId) {
			User user = member.getUser();
			this.setId(user.getId());
			this.setFirstName(user.getFirstName());
			this.setLastName(user.getLastName());
			this.setEmail(user.getEmail());
			this.setMember(new MemberDTO(member));
			this.setAttendeeId(attendeeId);
		}
		
		public UserDTO(User user, long attendeeId) {
			this.setId(user.getId());
			this.setFirstName(user.getFirstName());
			this.setLastName(user.getLastName());
			this.setEmail(user.getEmail());
			this.setAttendeeId(attendeeId);
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public MemberDTO getMember() {
			return member;
		}

		public void setMember(MemberDTO member) {
			this.member = member;
		}

		public long getAttendeeId() {
			return attendeeId;
		}

		public void setAttendeeId(long attendeeId) {
			this.attendeeId = attendeeId;
		}

		private class MemberDTO {
			
			private long id;
			private RoleEnum role;
			
			public MemberDTO(Member member) {
				this.setId(member.getId());
				this.setRole(member.getType());
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public RoleEnum getRole() {
				return role;
			}

			public void setRole(RoleEnum role) {
				this.role = role;
			}
		}
	}

	private class RegisteredDTO {
		
		private long id;
		private OffsetDateTime dateRegistered;
		private boolean isMember;
		private UserRegisteredDTO user;
		private RegisteredEnum status;
		
		public RegisteredDTO(Registered registered, boolean isMember, RegisteredEnum status) {
			this.setId(registered.getId());
			this.setDateRegistered(registered.getDateRegistered());
			this.setMember(isMember);
			this.setUser(new UserRegisteredDTO(registered.getUser()));
			this.setStatus(status);
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public OffsetDateTime getDateRegistered() {
			return dateRegistered;
		}

		public void setDateRegistered(OffsetDateTime dateRegistered) {
			this.dateRegistered = dateRegistered;
		}

		public boolean isMember() {
			return isMember;
		}

		public void setMember(boolean isMember) {
			this.isMember = isMember;
		}

		public UserRegisteredDTO getUser() {
			return user;
		}

		public void setUser(UserRegisteredDTO user) {
			this.user = user;
		}

		public RegisteredEnum getStatus() {
			return status;
		}

		public void setStatus(RegisteredEnum status) {
			this.status = status;
		}

		private class UserRegisteredDTO {
			
			private long id;
			private String firstName;
			private String lastName;
			private String email;
			
			public UserRegisteredDTO(User user) {
				this.setId(user.getId());
				this.setFirstName(user.getFirstName());
				this.setLastName(user.getLastName());
				this.setEmail(user.getEmail());
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public String getFirstName() {
				return firstName;
			}

			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}

			public String getLastName() {
				return lastName;
			}

			public void setLastName(String lastName) {
				this.lastName = lastName;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}
		}
	}
}
