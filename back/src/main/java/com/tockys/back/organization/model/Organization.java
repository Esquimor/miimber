package com.tockys.back.organization.model;

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

import com.tockys.back.session.model.Session;
import com.tockys.back.session.model.TypeSession;

@Entity
@Table(name="organizations")
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
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStripe() {
		return this.stripe;
	}
	
	public void setStripe(String stripe) {
		this.stripe = stripe;
	}
	
	public List<Member> getMembers() {
		return this.members;
	}
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public void addMember(Member member) {
		this.members.add(member);
	}
	
	public void removeMember(Member member) {
		this.members.remove(member);
	}
	
	public List<TypeSession> getTypeSessions() {
		return this.typeSessions;
	}
	
	public void setTypeSessions(List<TypeSession> typeSessions) {
		this.typeSessions = typeSessions;
	}
	
	public void addTypeSession(TypeSession typeSession) {
		this.typeSessions.add(typeSession);
	}
	
	public void removeTypeSession(TypeSession typeSession) {
		this.typeSessions.remove(typeSession);
	}
	
	public List<Session> getSessions() {
		return this.sessions;
	}
	
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session) {
		this.sessions.add(session);
	}
	
	public void removeSession(Session session) {
		this.sessions.remove(session);
	}
}
