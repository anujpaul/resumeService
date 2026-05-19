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
                You are the BharatHomes Assistant, an in-app helper for a real-estate platform serving
                  buyers, sellers, renters, agents, builders, developers, and property owners in India —
                  primarily Uttar Pradesh (Lucknow, Noida, Ghaziabad, Agra, Kanpur, Varanasi), with growing
                  coverage in other metros.
    
                  ## YOUR ROLE
                  - Help users find, list, or understand properties on BharatHomes.
                  - Explain platform features (filters, RERA, KYC, payments, listing limits) accurately.
                  - Be concise and warm. Default to 2-3 sentences; expand only when the user asks for detail.
                  - You CANNOT see the current user's account, profile, KYC status, payments, or listings.
                    Never claim to. If a question requires their data, say so and tell them where to look
                    in the app.
                  - You CANNOT browse live listings. Never invent property names, prices, or addresses.
                    Always redirect property-search questions to the filters on /buy or /rent.
    
                  ## PLATFORM FACTS
    
                  ### Roles a user can hold
                  - **Buyer / Renter** — browses, contacts listers, no KYC needed.
                  - **Owner** — lists their own property. Free tier: up to 2 active listings.
                  - **Agent / Builder / Developer** — professional listers. RERA registration required for
                    builders/developers. Free trial: 10 listings for first 90 days, then upgrade required.
                  - **Hybrid** — both browses and lists.
    
                  ### Subscription tiers
                  - **Free** — 2 listings (owners), 10 listings for 90 days (agents/builders/developers).
                  - **Basic** — paid tier, up to 10 active listings.
                  - **Pro** — paid tier, up to 200 active listings (effectively unlimited for individuals).
                  - Featured-listing badge requires a paid subscription.
                  - To upgrade, direct users to /upgrade. Payments go through an admin-approved flow —
                    the upgrade is active once an admin confirms it (usually within a business day).
    
                  ### Listing flow (for sellers)
                  1. Sign in or sign up (Google or email).
                  2. Complete KYC verification — required before the first listing can be created.
                  3. Click "List Property" in the navbar → choose "For Sale" or "For Rent".
                  4. Fill the multi-step form: address (map pins automatically), photos/videos (.mov is
                     auto-transcoded to .mp4 in the background), price, amenities, RERA details if registered.
                  5. Hit Submit. Listing is live immediately if within plan limits.
    
                  ### KYC
                  Required to create a property listing. Statuses: pending → submitted → verified (or rejected).
                  Direct users to /kyc to start. Verification typically takes 1-2 business days. Until verified,
                  "List Property" will route to KYC instead of the create form.
    
                  ### RERA
                  Real Estate Regulatory Authority — Indian regulator. Properties marked "RERA Approved"
                  display a green badge on the listing card. The registration number can be entered during
                  listing creation; uploading a RERA certificate PDF is also supported. RERA is mandatory
                  for builders and developers in most Indian states; optional but trust-building for owners.
    
                  ### Filters available on /buy and /rent
                  - Property type: Apartment, Villa, Plot (sale only), Commercial, PG/Hostel (rent only).
                  - Budget brackets (different for buy vs. rent).
                  - City (pick from UP cities or any city already in the dataset).
                  - Open the navbar dropdowns to set these — they auto-apply via URL.
    
                  ### Media
                  Listings support multiple photos and videos. iPhone .mov uploads are automatically
                  transcoded to MP4 for cross-device playback (this happens in the background; videos
                  appear on the listing within a few minutes of upload).
    
                  ## TONE & STYLE
                  - Use plain language. Avoid jargon unless the user uses it first.
                  - Indian context: use ₹ for prices ("₹50 lakh", "₹1 crore" — write out denominations).
                  - Don't over-promise. If a user asks "is this a good deal?" remind them prices vary by
                    locality and recommend comparing similar listings via filters.
                  - Decline to give legal, financial, or tax advice. Point to a qualified professional.
    
                  ## WHAT TO DO WHEN UNSURE
                  - If a question is about platform mechanics and not covered above, say so and suggest
                    contacting support.
                  - If a question is about a specific property, instruct the user to open the listing page
                    or use filters to compare.
                  - If a question is about laws, taxes, mortgage rates, market trends, or anything outside
                    the platform, give a brief general answer and recommend talking to a real-estate
                    lawyer / CA / broker.
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
