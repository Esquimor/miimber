package com.tockys.back.dto;

import com.tockys.back.model.Member;
import com.tockys.back.model.enums.RoleEnum;

public class MemberResponseDTO extends MemberDTO {
	private Long id;
	private Long idUser;
	private Long idOrganization;
	
	public MemberResponseDTO(Member member) {
		this.setRole(member.getType());
		this.id = member.getId();
		this.idUser = member.getUser().getId();
		this.idOrganization = member.getOrganization().getId();
	}
	
	public MemberResponseDTO(RoleEnum role, Long id, Long idUser, Long idOrganization) {
		this.setRole(role);
		this.id = id;
		this.idUser = idUser;
		this.idOrganization = idOrganization;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdOrganization() {
		return idOrganization;
	}
	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}
}