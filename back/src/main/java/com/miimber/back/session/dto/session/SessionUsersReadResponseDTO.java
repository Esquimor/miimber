package com.tockys.back.session.dto.session;

import java.util.ArrayList;
import java.util.List;

import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.session.model.Attendee;
import com.tockys.back.session.model.Session;
import com.tockys.back.user.dto.TemplateAttendeeDTO;
import com.tockys.back.user.model.User;

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
