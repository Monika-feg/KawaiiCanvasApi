package com.kawaiicanvas.kawaicanvas.Bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.Bot.model.ChatResponse;
import com.kawaiicanvas.kawaicanvas.Bot.model.ChatbotDto;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/bot")
    public String chatMessage(@RequestBody ChatbotDto chatbotDto) {

        ChatResponse response = chatService.sendChatResponse(chatbotDto.prompt(), chatbotDto.systemPrompt());
        return response.getChoices().get(0).getMessage().getContent();

    }

}
