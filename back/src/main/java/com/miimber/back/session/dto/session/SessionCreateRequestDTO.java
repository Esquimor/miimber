package com.miimber.back.session.dto.session;

import java.time.OffsetDateTime;
import java.util.List;

import com.miimber.back.session.enumItem.PeriodicityEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SessionCreateRequestDTO {

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
}
