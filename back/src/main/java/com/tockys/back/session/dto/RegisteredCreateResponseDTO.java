package com.tockys.back.dto;

import java.time.OffsetDateTime;

import com.tockys.back.model.Registered;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RegisteredEnum;

public class RegisteredCreateResponseDTO {

	private long id;
	private OffsetDateTime dateRegistered;
	private long sessionId;
	private boolean isMember;
	private UserDTO user;
	private RegisteredEnum status;
	
	public RegisteredCreateResponseDTO(Registered registered, boolean isMember, RegisteredEnum status) {
		this.setId(registered.getId());
		this.setDateRegistered(registered.getDateRegistered());
		this.setSessionId(registered.getSession().getId());
		this.setUser(new UserDTO(registered.getUser()));
		this.setMember(isMember);
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

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	
	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public RegisteredEnum getStatus() {
		return status;
	}

	public void setStatus(RegisteredEnum status) {
		this.status = status;
	}

	private class UserDTO {

		private long id;
		private String firstName;
		private String lastName;
		private String email;
		
		public UserDTO(User user) {
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
