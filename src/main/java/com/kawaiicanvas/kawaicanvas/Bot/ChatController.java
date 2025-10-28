package com.kawaiicanvas.kawaicanvas.Bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.Bot.model.ChatResponse;
import com.kawaiicanvas.kawaicanvas.Bot.model.ChatbotDto;
import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "https://kawaiicanvas.netlify.app" })
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/bot")
    public ResponseEntity<KawaiiResponse<String>> chatMessage(@RequestBody ChatbotDto chatbotDto) {
        try {
            ChatResponse response = chatService.sendChatResponse(chatbotDto.prompt(), chatbotDto.systemPrompt());
            return ResponseEntity.ok(new KawaiiResponse<>("Response successful",
                    response.getChoices().get(0).getMessage().getContent()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }

    }

}
