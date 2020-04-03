package com.tockys.back.organization.dto.member;

public class MemberByOrganizationRequestDTO extends MemberDTO {

	private Long idOrganization;
	private String email;
	private String firstName;
	private String lastName;

	public Long getIdOrganization() {
		return idOrganization;
	}
	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
}
