package com.anuj.resume.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatHome {
	
	@GetMapping("")
	public String home() {
		return "Application is up and running.";
	}

}
