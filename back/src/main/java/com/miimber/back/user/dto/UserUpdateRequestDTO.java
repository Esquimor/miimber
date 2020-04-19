package com.miimber.back.user.dto;

public class UserUpdateRequestDTO {

	private long id;
	private String email;
	private String firstName;
	private String lastName;

	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return this.firstName != null ? this.firstName : "";
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName != null ? this.lastName : "";
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
