package com.anuj.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuj.resume.model.ChatMessage;
import com.anuj.resume.service.ChatService;
import com.anuj.resume.service.StartupBlobLoader;


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
	public ResponseEntity<ChatMessage> greet(
			@RequestHeader("X-Session-Id") String sessionId,
			@RequestBody String message) {
		
		System.out.println(message);
		System.out.println("Session Id : " +sessionId);
		
		String userInput = "My name is " +message +"Greet me with only 1 line 8-15 words.";			
		
		String response = chatService.ask(sessionId, userInput, "I have asked for user name, Greet them if they replied with name");
		
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
	public ResponseEntity<ChatMessage> resumeQuestion(
			@RequestHeader("X-Session-Id") String sessionId,
			@RequestBody String userMessage) {
	
		System.out.println("Message is : " +userMessage);
		System.out.println("SessionId : " +sessionId);
		
		String systemString = """
									You are Anuj Paul’s personal chatbot.
									Answer based on the resume provided below. Keep it professional humour.
									Politely avoid answering user anything outside the resume. 
									If the topic is related to my resume, such as if they ask about GitLab, explain about my experiance on similar topics from my resume. 
									Only excepotion is if user ask about about AX and D365, answer as technical developer, otherwise avoid.
									Keep answers concise (under 200 words).									
									Here is my resume :
							""" + StartupBlobLoader.RESUME_CONTENT;

		String response = chatService.ask(sessionId, userMessage, systemString);
		return returnResponse(response);
	}

}