package com.miimber.back.forum.dto.subject;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.miimber.back.forum.model.CategoryForum;
import com.miimber.back.forum.model.PostForum;
import com.miimber.back.forum.model.SubjectForum;
import com.miimber.back.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubjectForumReadResponseDTO {

	private Long id;
	private String title;
	private CategoryDTO category;
	private List<PostDTO> posts;
	
	public SubjectForumReadResponseDTO(SubjectForum subject) {
		this.id = subject.getId();
		this.title = subject.getTitle();
		this.category = new CategoryDTO(subject.getCategory());
		this.posts = new ArrayList<PostDTO>();
		for(PostForum post: subject.getPosts()) {
			this.posts.add(new PostDTO(post));
		}
	}
	
	@Getter @Setter
	private class CategoryDTO {
		
		private Long id;
		private String title;
		
		public CategoryDTO(CategoryForum category) {
			this.id = category.getId();
			this.title = category.getTitle();
		}
	}
	
	@Getter @Setter
	private class PostDTO {
		
		private Long id;
		private String post;
		private OffsetDateTime datePost;
		private UserDTO user;
		
		public PostDTO(PostForum post) {
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
}
