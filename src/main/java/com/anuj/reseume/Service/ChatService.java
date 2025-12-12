package com.anuj.reseume.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	@Autowired
	BlobService service;
	
	private final ChatClient chatClient;
	
	 public ChatService(ChatClient.Builder chatClientBuilder) {
	        this.chatClient = chatClientBuilder.build();
	    }

	 public String ask(String userMessage, String systemString) {
		 
	
		 
		return this.chatClient.prompt()
				.system(systemString)
	            .user(userMessage)
	            .call()
	            .content();
	 }
}
