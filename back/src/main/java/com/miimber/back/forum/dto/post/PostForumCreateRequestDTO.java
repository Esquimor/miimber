package com.miimber.back.forum.dto.post;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForumCreateRequestDTO {

	@NotNull
	private String post;
	@NotNull
	private OffsetDateTime datePost;
	@NotNull
	private Long idSubject;
}
