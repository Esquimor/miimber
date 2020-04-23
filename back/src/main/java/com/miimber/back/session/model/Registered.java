package com.miimber.back.session.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.miimber.back.organization.model.Member;
import com.miimber.back.session.model.enums.RegisteredEnum;
import com.miimber.back.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="registered")
@Getter @Setter
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
}
