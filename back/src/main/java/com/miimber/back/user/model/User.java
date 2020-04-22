package com.miimber.back.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miimber.back.organization.model.Member;
import com.miimber.back.session.model.Attendee;
import com.miimber.back.session.model.Registered;
import com.miimber.back.user.model.enums.StatusEnum;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "status")
	private StatusEnum status = StatusEnum.Waiting;
	
	@Column(name = "tokenCreation")
	private String tokenCreation;
	
	@Column(name = "tokenPasswordForgotten")
	private String tokenPasswordForgotten;
	
	@Column(name = "tokenChangeEmail")
	private String tokenChangeEmail;
	
	@OneToMany(mappedBy = "user")
    private List<Member> members;
	
	@OneToMany(mappedBy = "user")
    private List<Attendee> attendees;
	
	@OneToMany(mappedBy = "user")
    private List<Registered> registereds;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public StatusEnum getStatus() {
		return this.status;
	}
	
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public String getTokenCreation() {
		return this.tokenCreation;
	}
	
	public void setTokenCreation(String tokenCreation) {
		this.tokenCreation = tokenCreation;
	}
	
	public String getTokenPasswordForgotten() {
		return this.tokenPasswordForgotten;
	}
	
	public void setTokenPasswordForgotten(String tokenPasswordForgotten) {
		this.tokenPasswordForgotten = tokenPasswordForgotten;
	}
	
	public String getTokenChangeEmail() {
		return this.tokenChangeEmail;
	}
	
	public void setTokenChangeEmail(String tokenChangeEmail) {
		this.tokenChangeEmail = tokenChangeEmail;
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
	
	public List<Attendee> getAttendees() {
		return this.attendees;
	}
	
	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}
	
	public void addAttendee(Attendee attendee) {
		this.attendees.add(attendee);
	}
	
	public void removeAttendee(Attendee attendee) {
		this.attendees.remove(attendee);
	}
	
	public List<Registered> getRegistereds() {
		return this.registereds;
	}
	
	public void setRegistereds(List<Registered> registereds) {
		this.registereds = registereds;
	}
	
	public void addRegistered(Registered registered) {
		this.registereds.add(registered);
	}
	
	public void removeRegistered(Registered registered) {
		this.registereds.remove(registered);
	}
}
