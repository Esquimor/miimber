package com.tockys.back.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sessions")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id; 
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "startDate", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime start;
	
	@Column(name = "endDate", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime end;
    
    @ManyToOne
    private Organization organization;
    
    @ManyToOne
    private TemplateSession templateSession;
    
    @ManyToOne
    private TypeSession typeSession;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public OffsetDateTime getStart() {
		return this.start;
	}
	
	public void setStart(OffsetDateTime start) {
		this.start = start;
	}
	
	public OffsetDateTime getEnd() {
		return this.end;
	}
	
	public void setEnd(OffsetDateTime end) {
		this.end = end;
	}
	
	public Organization getOrganization() {
		return this.organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public TemplateSession getTemplateSession() {
		return this.templateSession;
	}
	
	public void setTemplateSession(TemplateSession templateSession) {
		this.templateSession = templateSession;
	}
	
	public TypeSession getTypeSession() {
		return this.typeSession;
	}
	
	public void setTypeSession(TypeSession typeSession) {
		this.typeSession = typeSession;
	}
}
