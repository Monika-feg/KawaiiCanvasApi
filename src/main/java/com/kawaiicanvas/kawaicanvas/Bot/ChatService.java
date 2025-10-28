package com.kawaiicanvas.kawaicanvas.Bot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kawaiicanvas.kawaicanvas.Bot.model.ChatBotResponseEnum;
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

            ChatBotResponseEnum responseEnum = ChatBotResponseEnum.valueOf(systemPrompt);
            String botReply = responseEnum.getSystemPrompt(); // alltid det fördefinierade

            // Skapa ett ChatResponse med det fasta svaret
            ChatResponse response = new ChatResponse(
                    java.util.List.of(new ChatResponse.Choice(
                            new com.kawaiicanvas.kawaicanvas.Bot.model.Message("bot", botReply))));
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
