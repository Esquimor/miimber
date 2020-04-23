package com.miimber.back.organization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.miimber.back.organization.model.enums.RoleEnum;
import com.miimber.back.user.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="members")
@Getter @Setter
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
	
	public boolean canEmergeOrganization() {
		if (this.role == RoleEnum.MEMBER || this.role == RoleEnum.OFFICE) {
			return false;
		}
		return true;
	}
}
