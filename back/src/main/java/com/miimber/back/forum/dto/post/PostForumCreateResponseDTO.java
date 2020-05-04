package com.miimber.back.forum.dto.post;

import java.time.OffsetDateTime;

import com.miimber.back.forum.model.PostForum;
import com.miimber.back.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForumCreateResponseDTO {

	private Long id;
	private String post;
	private OffsetDateTime datePost;
	private UserDTO user;
	
	public PostForumCreateResponseDTO(PostForum post) {
		this.id = post.getId();
		this.post = post.getPost();
		this.datePost = post.getDatePost();
		this.user = new UserDTO(post.getUser());
	}
	
	@Getter @Setter
	private class UserDTO {
		
		private Long id;
		private String firstName;
		private String lastName;
		private String email;
		
		public UserDTO(User user) {
			this.id = user.getId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.email = user.getEmail();
		}
	}
}
