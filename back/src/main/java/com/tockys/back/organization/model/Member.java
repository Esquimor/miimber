package com.tockys.back.organization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.user.model.User;

@Entity
@Table(name="members")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @ManyToOne
    private User user;
    
	private RoleEnum role = RoleEnum.MEMBER;
    
    @ManyToOne
    private Organization organization;
	
	public boolean canEditOrganization() {
		if (this.role == RoleEnum.MEMBER || this.role == RoleEnum.INSTRUCTOR) {
			return false;
		}
		return true;
	}
	
	public long getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public RoleEnum getType() {
		return this.role;
	}
	
	public void setType(RoleEnum role) {
		this.role = role;
	}
	
	public Organization getOrganization() {
		return this.organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
