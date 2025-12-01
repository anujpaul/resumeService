package com.anuj.reseume.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuj.reseume.model.ChatMessage;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@GetMapping("/")
	public String home() {
		return "Welcome home!";
	}
	
	@PostMapping("greet")
	public ResponseEntity<ChatMessage> greet(@RequestBody String message) {
		System.out.println(message);
		try {
			//throw new  RuntimeException("Improper structure");
	    	return new ResponseEntity<>(new ChatMessage(message), HttpStatus.OK);
	    	}
		
		catch(Exception e) {
			return new ResponseEntity<>(new ChatMessage("Something went wrong : " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
