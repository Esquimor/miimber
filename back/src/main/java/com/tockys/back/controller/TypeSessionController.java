package com.tockys.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.service.TypeSessionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TypeSessionController {
	
	@Autowired
	private TypeSessionService typeSessionService;
}
