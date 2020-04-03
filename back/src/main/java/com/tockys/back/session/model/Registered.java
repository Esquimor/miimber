package com.tockys.back.session.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tockys.back.session.model.enums.RegisteredEnum;
import com.tockys.back.user.model.User;

@Entity
@Table(name="registered")
public class Registered implements Comparable<Registered> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Session session;
	
	@ManyToOne
	private User user;

	@Column(name = "dateRegistered")
	private OffsetDateTime dateRegistered;

	@Override
	public int compareTo(Registered o) {
		return dateRegistered.compareTo(o.getDateRegistered());
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Session getSession() {
		return this.session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public OffsetDateTime getDateRegistered() {
		return this.dateRegistered;
	}
	
	public void setDateRegistered(OffsetDateTime dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
}
