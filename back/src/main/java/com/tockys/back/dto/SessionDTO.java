package com.tockys.back.dto;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.tockys.back.model.Session;
import com.tockys.back.model.TypeSession;

public class SessionDTO {

	public long id;
	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private long organizationId;
	private long templateSessionId;
	private TypeSessionDTO typeSession;
	
	public SessionDTO(Session session) {
		this.id = session.getId();
		this.title = session.getTitle();
		this.description = session.getDescription();
		this.start = session.getStart();
		this.end = session.getEnd();
		this.organizationId = session.getOrganization().getId();
		this.typeSession = new TypeSessionDTO(session.getTypeSession());
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
