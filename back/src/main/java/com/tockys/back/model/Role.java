package com.tockys.back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tockys.back.model.enums.RoleEnum;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "type")
	private RoleEnum type = RoleEnum.MEMBER;
	
	public long getId() {
		return this.id;
	}
	
	public RoleEnum getType() {
		return this.type;
	}
	
	public void setType(RoleEnum type) {
		this.type = type;
	}
}
