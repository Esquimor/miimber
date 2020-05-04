package com.miimber.back.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.miimber.back.core.helper.Helper;
import com.miimber.back.forum.dto.post.PostForumCreateRequestDTO;
import com.miimber.back.forum.dto.post.PostForumCreateResponseDTO;
import com.miimber.back.forum.model.PostForum;
import com.miimber.back.forum.model.SubjectForum;
import com.miimber.back.forum.service.PostForumService;
import com.miimber.back.forum.service.SubjectForumService;
import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.service.MemberService;
import com.miimber.back.user.model.User;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostForumController {
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PostForumService postService;
	
	@Autowired
	private SubjectForumService subjectService;

	@RequestMapping(value = "/organization/{idOrg}/forum/post/", method = RequestMethod.POST)
	public ResponseEntity<?> createPost(@PathVariable Long idOrg, @RequestBody PostForumCreateRequestDTO postDto) throws Exception {
		User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        Member member = memberService.getMemberByOrganizationIdAndByUser(idOrg, user);
        
        if (member == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        SubjectForum subject = subjectService.get(postDto.getIdSubject());
        if (subject == null) {
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (subject.getCategory().getOrganization().getId() != idOrg) {
        	return new ResponseEntity(HttpStatus.CONFLICT);
        }
        PostForum post = new PostForum();
        post.setDatePost(postDto.getDatePost());
        post.setPost(post.getPost());
        post.setSubject(subject);
        post.setUser(user);
        
        return ResponseEntity.ok(new PostForumCreateResponseDTO(postService.create(post)));
	}
}
