package com.miimber.back.session.dto.session;

import java.time.OffsetDateTime;

public class SessionEditRequestDTO {

	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private long typeSessionId;
	private int limit;

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
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
