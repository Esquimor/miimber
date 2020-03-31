package com.tockys.back.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tockys.back.model.Attendee;
import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.Session;
import com.tockys.back.model.TypeSession;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RoleEnum;

public class SessionReadDTO {

	private long id;
	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private OrganizationDTO organization;
	private TypeSessionDTO typeSession;
	private MemberUserDTO member;
	private List<UserDTO> users;
	
	public SessionReadDTO(Session session, Member member) {
		this.setId(session.getId());
		this.setTitle(session.getTitle());
		this.setDescription(session.getDescription());
		this.setStart(session.getStart());
		this.setEnd(session.getEnd());
		this.setOrganization(new OrganizationDTO(session.getOrganization()));
		this.setTypeSession(new TypeSessionDTO(session.getTypeSession()));
		this.setMember(new MemberUserDTO(member));
		if (member != null) {
			this.setMember(new MemberUserDTO(member));
			
			if (member.getType() == RoleEnum.INSTRUCTOR || member.getType() == RoleEnum.OFFICE_INSTRUCTOR || member.getType() == RoleEnum.OWNER) {
				this.users = new ArrayList<UserDTO>();
				List<Member> members = session.getOrganization().getMembers();
				List<Attendee> attendees = session.getAttendees();
				for(Member memberOrganization: members) {
					Long attendeId = 0L;
					for(Attendee attendee: attendees) {
						if (attendee.getUser().getId() == member.getUser().getId()) {
							attendeId = attendee.getId();
							break;
						}
					}
					this.users.add(new UserDTO(memberOrganization, attendeId));
				}
				
				for(Attendee attendee: attendees) {
					User user = attendee.getUser();
					boolean isMember = false;
					for(Member memberToTest: members) {
						if (memberToTest.getUser().getId() == user.getId()) {
							isMember = true;
							break;
						}
					}
					if (isMember == false) {
						this.users.add(new UserDTO(user, attendee.getId()));
					}
				}
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

	public MemberUserDTO getMember() {
		return member;
	}

	public void setMember(MemberUserDTO member) {
		this.member = member;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
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
}
