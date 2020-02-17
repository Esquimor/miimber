package com.tockys.back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="members")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;
	
	public long getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Organization getOrganization() {
		return this.organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
