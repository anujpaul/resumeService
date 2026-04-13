package com.anuj.resume.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
//	@Autowired
//	BlobService service;
	
	private final ChatClient chatClient;
	
	 public ChatService(ChatClient.Builder chatClientBuilder) {
	        this.chatClient = chatClientBuilder.build();
	    }

	 public String ask(String sessionId, String userMessage, String systemString) {
		 
	
		 
		return this.chatClient.prompt()
				.system(systemString)
	            .user(userMessage)
	            .call()
	            .content();
	 }
}
