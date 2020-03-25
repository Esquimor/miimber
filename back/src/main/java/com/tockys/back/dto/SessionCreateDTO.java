package com.tockys.back.dto;

import java.time.OffsetDateTime;

public class SessionCreateDTO {

	private OffsetDateTime start;
	private OffsetDateTime end;
	private long typeSessionId;
	private long organizationId;
	

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
	public long getTypeSessionId() {
		return typeSessionId;
	}
	public void setTypeSessionId(long typeSessionId) {
		this.typeSessionId = typeSessionId;
	}
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
}
