package com.miimber.back.session.dto.session;

import java.util.ArrayList;
import java.util.List;

import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.enums.RoleEnum;
import com.miimber.back.session.model.Attendee;
import com.miimber.back.session.model.Session;
import com.miimber.back.user.dto.TemplateAttendeeDTO;
import com.miimber.back.user.model.User;

public class SessionUsersReadResponseDTO {
		
	private long id;
	private String title;
	private List<TemplateAttendeeDTO> users;
	
	public SessionUsersReadResponseDTO(Session session, List<TemplateAttendeeDTO> users) {
		this.setId(session.getId());
		this.setTitle(session.getTitle());
		this.setUsers(users);
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

	public List<TemplateAttendeeDTO> getUsers() {
		return users;
	}

	public void setUsers(List<TemplateAttendeeDTO> users) {
		this.users = users;
	}
}
