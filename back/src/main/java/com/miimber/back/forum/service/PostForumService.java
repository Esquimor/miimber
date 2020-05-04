package com.miimber.back.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miimber.back.forum.model.PostForum;
import com.miimber.back.forum.repository.PostForumRepository;

@Service
public class PostForumService implements IPostForumService {

	@Autowired
	private PostForumRepository postRepository;
	
	@Override
	public PostForum create(PostForum entity) {
		return postRepository.save(entity);
	}

	@Override
	public PostForum update(PostForum entity) {
		return postRepository.save(entity);
	}

	@Override
	public void delete(PostForum entity) {
		postRepository.delete(entity);
	}

	@Override
	public PostForum get(Long id) {
		Optional<PostForum> post = postRepository.findById(id);
		if (post.isEmpty())	 {
			return null;
		}
		return post.get();
	}

}
