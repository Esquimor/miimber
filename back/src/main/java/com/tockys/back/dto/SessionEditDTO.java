package com.tockys.back.dto;

import java.time.OffsetDateTime;

public class SessionEditDTO {

	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private long typeSessionId;

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
}
