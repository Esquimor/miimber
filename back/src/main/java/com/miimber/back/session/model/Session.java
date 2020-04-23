package com.miimber.back.session.model;

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

import com.miimber.back.organization.model.Organization;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sessions")
@Getter @Setter
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id; 
	
	@Column(name = "title", length = 255, nullable = false)
	private String title;
	
	@Column(name = "description", columnDefinition="TEXT", length = 5000, nullable = false)
	private String description;
	
	@Column(name = "startDate", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private OffsetDateTime start;
	
	@Column(name = "endDate", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private OffsetDateTime end;
	
	@Column(name = "limitUsers", nullable = false)
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
	
	public void addAttendee(Attendee attendee) {
		this.attendees.add(attendee);
	}
	
	public void removeAttendee(Attendee attendee) {
		this.attendees.remove(attendee);
	}
	
	public void addRegistered(Registered registered) {
		this.registereds.add(registered);
	}
	
	public void removeRegistered(Registered registered) {
		this.registereds.remove(registered);
	}
}
