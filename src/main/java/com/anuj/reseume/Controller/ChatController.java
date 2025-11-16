package com.anuj.reseume.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	
	@GetMapping("/")
	public String home() {
		return "Welcome home!";
	}
	
	@GetMapping("Hello")
	public String Hello() {
		return "Hello World";
	}

}
