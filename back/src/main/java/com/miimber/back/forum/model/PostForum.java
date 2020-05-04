package com.miimber.back.forum.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.miimber.back.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="posts_forum")
@Getter @Setter
public class PostForum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name= "post")
	private String post;
	
	@Column(name = "datePost")
	private OffsetDateTime datePost;
	
    @ManyToOne
	@NotNull
	private SubjectForum subject;
    
    @ManyToOne
	private User user;
}
