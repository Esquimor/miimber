package com.tockys.back.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.tockys.back.enumItem.PeriodicityEnum;

public class SessionCreateDTO {

	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private PeriodicityEnum periodicity;
	private List<Integer> days;
	private int repeat;
	private long typeSessionId;
	private long organizationId;
	private int limit;
	

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
	public OffsetDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}
	public OffsetDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}
	public PeriodicityEnum getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(PeriodicityEnum periodicity) {
		this.periodicity = periodicity;
	}
	public List<Integer> getDays() {
		return days;
	}
	public void setDays(List<Integer> days) {
		this.days = days;
	}
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
