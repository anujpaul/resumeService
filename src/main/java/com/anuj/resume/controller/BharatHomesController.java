package com.anuj.resume.controller;

import com.anuj.resume.model.ChatMessage;
import com.anuj.resume.model.LocationCoordinates;
import com.anuj.resume.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.Location;

@RestController
@RequestMapping("/ai/bharathomes")
public class BharatHomesController {

    @Autowired
    ChatService chatService;
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to bharat homes!");
    }

    @PostMapping("greet")
    public ResponseEntity<ChatMessage> greet(@RequestBody String message)
    {
        String userInput = "My name is " +message + "Greet me";

        String response = chatService.ask("", userInput, "I have asked for user name, Greet them if they replied with name");

        return new ResponseEntity<>(new ChatMessage(response), HttpStatus.OK);

    }

    @PostMapping("chat")
    public ResponseEntity<ChatMessage> chat(@RequestBody ChatMessage request)
    {
        String systemPrompt = """
                You are the BharatHomes Assistant, helping users navigate buying, renting, or listing property 
                in India (especially Uttar Pradesh: Lucknow, Noida, Ghaziabad, Agra, Kanpur, Varanasi). 
                You help all roles: buyers, sellers, renters, agents, and listers. 
                Be concise, factual, and friendly. 
                When users ask about a specific property feature (price, location, amenities), 
                suggest they check the listing or filters. For RERA, KYC, payment, or platform-specific questions, 
                give accurate information. If you don't know something specific to BharatHomes, 
                say so and suggest contacting support.
                """;

        String response = chatService.ask(
                "session-"+System.currentTimeMillis(),
                request.getMessage(),
                systemPrompt );

        ChatMessage chatMessage = new ChatMessage(response);

        return ResponseEntity.ok(chatMessage);

    }

    @PostMapping("location")
    public ResponseEntity<String> location(@RequestBody ChatMessage request){
        String systemPrompt = """
            You are a geocoding assistant. Extract latitude and longitude from the address.
            Return ONLY a valid JSON object with exactly these fields:
            {
                "latitude": number,
                "longitude": number,
                "address": "string",
            }
            Do not include any other text, markdown, or explanations.
            If coordinates not found, return null values.
            """;

        String userPrompt = "Find coordinates for: " + request.getMessage();

        String response = chatService.ask(
                "geocoding-session",
                userPrompt,
                systemPrompt );

        return ResponseEntity.ok(response);
    }
}
