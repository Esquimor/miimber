package com.miimber.back.organization.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.miimber.back.organization.model.enums.RoleEnum;
import com.miimber.back.session.model.Session;
import com.miimber.back.session.model.TypeSession;
import com.miimber.back.user.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="organizations")
@Getter @Setter
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "stripe")
	private String stripe;
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    private List<Member> members;
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    private List<TypeSession> typeSessions;
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    private List<Session> sessions;
	
	public Organization() {
		this.name = "";
		this.members = new ArrayList<Member>();
		this.typeSessions = new ArrayList<TypeSession>();
		this.sessions = new ArrayList<Session>();
	}
	
	public Organization(String name) {
		this.name = name;
		this.members = new ArrayList<Member>();
		this.typeSessions = new ArrayList<TypeSession>();
		this.sessions = new ArrayList<Session>();
	}

	public void addMember(Member newMember) {
		this.members.add(newMember);
	}
}
