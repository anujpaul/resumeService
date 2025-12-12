package com.anuj.reseume.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatHome {
	
	@GetMapping("")
	public String home() {
		return "Application is up and running.";
	}

}
