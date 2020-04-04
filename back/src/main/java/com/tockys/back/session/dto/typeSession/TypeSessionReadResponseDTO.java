package com.tockys.back.session.dto.typeSession;

import com.tockys.back.session.model.TypeSession;

public class TypeSessionReadResponseDTO {

	private long id;
	private String name;
	
	public TypeSessionReadResponseDTO(TypeSession typeSession) {
		this.id = typeSession.getId();
		this.name = typeSession.getName();
	}
	
	public TypeSessionReadResponseDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
