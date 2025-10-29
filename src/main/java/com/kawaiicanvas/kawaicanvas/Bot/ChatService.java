package com.kawaiicanvas.kawaicanvas.Bot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kawaiicanvas.kawaicanvas.Bot.model.ChatRequest;
import com.kawaiicanvas.kawaicanvas.Bot.model.ChatResponse;
import com.openai.errors.OpenAIException;

@Service
public class ChatService {

    @Value("${openai.api.url}")
    private String apiUrl;

    public final RestTemplate restTemplate;

    public ChatService(@Qualifier("openAiRestTemplate") RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public ChatResponse sendChatResponse(String prompt, String systemPrompt) {
        try {

            ChatRequest chatRequest = new ChatRequest("gpt-4o", prompt, systemPrompt, 1);
            ChatResponse response = restTemplate.postForObject(apiUrl, chatRequest, ChatResponse.class);
            return response;
            // tog insporation från denna
            // https://www.baeldung.com/spring-rest-template-error-handling
            // och testade mig fram
        } catch (HttpServerErrorException e) {
            throw new OpenAIException(" Server error: " + e.getMessage());
        } catch (RestClientException e) {
            throw new OpenAIException(" Unexpekted error calling openAI: " + e.getMessage());
        }

    }
}
