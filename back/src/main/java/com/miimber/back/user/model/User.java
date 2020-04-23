package com.miimber.back.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miimber.back.organization.model.Member;
import com.miimber.back.session.model.Attendee;
import com.miimber.back.session.model.Registered;
import com.miimber.back.user.model.enums.StatusEnum;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter @Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "email", length = 60, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;
	
	@Column(name = "first_name", length = 40, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 40, nullable = false)
	private String lastName;
	
	@Column(name = "status", nullable = false)
	private StatusEnum status = StatusEnum.Waiting;
	
	@Column(name = "newEmail", length = 60)
	private String newEmail;
	
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
	
	public void addMember(Member member) {
		this.members.add(member);
	}
	
	public void removeMember(Member member) {
		this.members.remove(member);
	}
	
	public void addAttendee(Attendee attendee) {
		this.attendees.add(attendee);
	}
	
	public void removeAttendee(Attendee attendee) {
		this.attendees.remove(attendee);
	}
	
	public void addRegistered(Registered registered) {
		this.registereds.add(registered);
	}
	
	public void removeRegistered(Registered registered) {
		this.registereds.remove(registered);
	}
}
