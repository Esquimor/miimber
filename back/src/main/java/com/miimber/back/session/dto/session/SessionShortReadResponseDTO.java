package com.miimber.back.session.dto.session;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.miimber.back.session.model.Session;
import com.miimber.back.session.model.TypeSession;

public class SessionShortReadResponseDTO {

	public long id;
	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private long organizationId;
	private long templateSessionId;
	private TypeSessionDTO typeSession;
	private int limit;
	private int nbRegistereds;
	
	public SessionShortReadResponseDTO(Session session) {
		this.id = session.getId();
		this.title = session.getTitle();
		this.description = session.getDescription();
		this.start = session.getStart();
		this.end = session.getEnd();
		this.organizationId = session.getOrganization().getId();
		this.typeSession = new TypeSessionDTO(session.getTypeSession());
		this.setLimit(session.getLimit());
		if (session.getRegistereds() == null) {
			this.setNbRegistereds(0);
		} else {
			this.setNbRegistereds(session.getRegistereds().size());
		}
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public OffsetDateTime getStart() {
		return this.start;
	}

	public void setStart(OffsetDateTime start) {
		this.start = start;
	}

	public OffsetDateTime getEnd() {
		return this.end;
	}

	public void setEnd(OffsetDateTime end) {
		this.end = end;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public long getTemplateSessionId() {
		return templateSessionId;
	}

	public void setTemplateSessionId(long templateSessionId) {
		this.templateSessionId = templateSessionId;
	}
	
	public TypeSessionDTO getTypeSession() {
		return typeSession;
	}

	public void setTypeSession(TypeSessionDTO typeSession) {
		this.typeSession = typeSession;
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

	private class TypeSessionDTO {

		private long id;
		private String name;
		
		public TypeSessionDTO(TypeSession typeSession) {
			this.id = typeSession.getId();
			this.name = typeSession.getName();
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
}
