package com.tockys.back.organization.dto.member;

public class MemberRequestDTO extends MemberDTO {

	private Long idOrganization;
	private Long idUser;
	public Long getIdOrganization() {
		return idOrganization;
	}
	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
}
