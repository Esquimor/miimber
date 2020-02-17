package com.tockys.back.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="organizations")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "organization")
    private List<Member> members;
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
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
