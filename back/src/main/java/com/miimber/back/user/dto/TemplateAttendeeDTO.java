package com.miimber.back.user.dto;

import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.enums.RoleEnum;
import com.miimber.back.user.model.User;

public class TemplateAttendeeDTO {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private MemberDTO member;
	private long attendeeId;
	private boolean isRegistered;
	
	public TemplateAttendeeDTO(Member member, long attendeeId, boolean isRegistered) {
		User user = member.getUser();
		this.setId(user.getId());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setMember(new MemberDTO(member));
		this.setAttendeeId(attendeeId);
		this.setRegistered(isRegistered);
	}
	
	public TemplateAttendeeDTO(User user, long attendeeId, boolean isRegistered) {
		this.setId(user.getId());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setAttendeeId(attendeeId);
		this.setRegistered(isRegistered);
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

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
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

