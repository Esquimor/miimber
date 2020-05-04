package com.miimber.back.forum.repository;

import org.springframework.data.repository.CrudRepository;

import com.miimber.back.forum.model.PostForum;

public interface PostForumRepository extends CrudRepository<PostForum, Long> {

}
