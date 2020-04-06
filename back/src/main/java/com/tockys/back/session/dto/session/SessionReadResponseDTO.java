package com.tockys.back.session.dto.session;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.session.model.Attendee;
import com.tockys.back.session.model.Registered;
import com.tockys.back.session.model.Session;
import com.tockys.back.session.model.TypeSession;
import com.tockys.back.session.model.enums.RegisteredEnum;
import com.tockys.back.user.model.User;

public class SessionReadResponseDTO {

	private long id;
	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private OrganizationDTO organization;
	private TypeSessionDTO typeSession;
	private MeDTO me;
	private List<RegisteredDTO> registereds;
	private int limit;
	
	public SessionReadResponseDTO(Session session, Member member, Long userId) {
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
