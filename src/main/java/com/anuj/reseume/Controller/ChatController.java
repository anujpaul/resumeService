package com.anuj.reseume.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuj.reseume.model.ChatMessage;


@CrossOrigin(origins = {"http://localhost:4200", "https://anujpaul.github.io", "https://anujpaulresume.azurewebsites.net"})
@RestController
@RequestMapping("/chat")
public class ChatController {
	
	private final ChatClient chatClient;
	
	 public ChatController(ChatClient.Builder chatClientBuilder) {
	        this.chatClient = chatClientBuilder.build();
	    }
	
	@GetMapping("/")
	public String home() {
		return "Welcome home!";
	}
	
	@PostMapping("greet")
	public ResponseEntity<ChatMessage> greet(@RequestBody String message) {
		System.out.println(message);
		
		String userInput = "My name is " +message +"Greet me with only 1 line 8-15 words.";
		
		String response = this.chatClient.prompt()
	            .user(userInput)
	            .call()
	            .content();
		try {
			//throw new  RuntimeException("Improper structure");
	    	return new ResponseEntity<>(new ChatMessage(response), HttpStatus.OK);
	    	}
		
		catch(Exception e) {
			return new ResponseEntity<>(new ChatMessage("Something went wrong : " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
