package com.kawaiicanvas.kawaicanvas.Bot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kawaiicanvas.kawaicanvas.Bot.model.ChatRequest;
import com.kawaiicanvas.kawaicanvas.Bot.model.ChatResponse;

@Service
public class ChatService {

    @Value("${openai.api.url}")
    private String apiUrl;

    public final RestTemplate restTemplate;

    public ChatService(@Qualifier("openAiRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChatResponse sendChatResponse(String prompt, String systemPrompt) {
        ChatRequest chatRequest = new ChatRequest("gpt-4o", prompt, systemPrompt, 1);

        ChatResponse chatResponse = restTemplate.postForObject(apiUrl, chatRequest, ChatResponse.class);

        return chatResponse;
    }

}
