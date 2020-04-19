package com.miimber.back.user.dto;

import java.time.OffsetDateTime;

import com.miimber.back.session.model.Session;

public class UserSessionReadResponseDTO {

	private long id; 
	private String title;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private String typeSessionName;
	private String organizationName;
	private int limit;
	private int nbRegistereds;
	
	public UserSessionReadResponseDTO(Session session) {
		this.setId(session.getId());
		this.setTitle(session.getTitle());
		this.setStart(session.getStart());
		this.setEnd(session.getEnd());
		this.setTypeSessionName(session.getTypeSession().getName());
		this.setOrganizationName(session.getOrganization().getName());
		this.setLimit(session.getLimit());
		this.setNbRegistereds(session.getRegistereds().size());
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

	public String getTypeSessionName() {
		return typeSessionName;
	}

	public void setTypeSessionName(String typeSessionName) {
		this.typeSessionName = typeSessionName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getNbRegistereds() {
		return nbRegistereds;
	}

	public void setNbRegistereds(int nbRegistereds) {
		this.nbRegistereds = nbRegistereds;
	}
}
