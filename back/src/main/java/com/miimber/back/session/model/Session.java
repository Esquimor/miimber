package com.tockys.back.session.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tockys.back.organization.model.Organization;

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
	
	@Column(name = "limitUsers")
	private int limit;
	
	@OneToMany(mappedBy = "session")
    private List<Attendee> attendees;
	
	@OneToMany(mappedBy = "session")
    private List<Registered> registereds;
    
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
	
	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
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
	
	public List<Attendee> getAttendees() {
		return this.attendees;
	}
	
	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}
	
	public void addAttendee(Attendee attendee) {
		this.attendees.add(attendee);
	}
	
	public void removeAttendee(Attendee attendee) {
		this.attendees.remove(attendee);
	}
	
	public List<Registered> getRegistereds() {
		return this.registereds;
	}
	
	public void setRegistereds(List<Registered> registereds) {
		this.registereds = registereds;
	}
	
	public void addRegistered(Registered registered) {
		this.registereds.add(registered);
	}
	
	public void removeRegistered(Registered registered) {
		this.registereds.remove(registered);
	}
}
