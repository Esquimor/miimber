package com.tockys.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tockys.back.model.enums.UserRoleEnum;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "role")
	private UserRoleEnum role = UserRoleEnum.User;
	
	@OneToMany(mappedBy = "user")
    private List<Member> members;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
	
	public UserRoleEnum getRole() {
		return this.role;
	}
	
	public void setRole(UserRoleEnum role) {
		this.role = role;
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
}
