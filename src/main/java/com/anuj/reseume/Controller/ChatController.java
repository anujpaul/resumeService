package com.anuj.reseume.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuj.reseume.Service.ChatService;
import com.anuj.reseume.model.ChatMessage;


@CrossOrigin(origins = {"http://localhost:4200", "https://anujpaul.github.io", "https://anujpaulresume.azurewebsites.net"})
@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	
	
	@GetMapping("/")
	public String home() {
		return "Welcome home!";
	}
	
	@PostMapping("greet")
	public ResponseEntity<ChatMessage> greet(@RequestBody String message) {
		
		System.out.println(message);
		
		String userInput = "My name is " +message +"Greet me with only 1 line 8-15 words.";			
		
		String response = chatService.greet(userInput);
		
		return returnResponse(response);
	}

	private ResponseEntity<ChatMessage> returnResponse(String response) {
		try {
	    	return new ResponseEntity<>(new ChatMessage(response), HttpStatus.OK);
	    	}
		
		catch(Exception e) {
			return new ResponseEntity<>(new ChatMessage("Something went wrong : " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("resumeQuestion")
	public ResponseEntity<ChatMessage> resumeQuestion(@RequestBody String message) {
	
		System.out.println("Message is : " +message);
		
		String resume = chatService.getResume();
		
		String userInput = "You are my chatBot and need to answer from my resume, this is my resume " +resume +"This is the question asked by user : " +message+" Answer only 1 line 20-30 words about my resume, say I can answer only about Anuj's Resume if something else is asked.";	
		
		String response = chatService.greet(userInput);
		return returnResponse(response);
	}

}
