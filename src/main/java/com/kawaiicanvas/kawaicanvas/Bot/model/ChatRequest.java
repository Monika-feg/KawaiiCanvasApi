package com.kawaiicanvas.kawaicanvas.Bot.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n;

    public ChatRequest(String model, String prompt, String systemPrompt, int n) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", ChatBotResponseEnum.valueOf(systemPrompt).getSystemPrompt()));
        this.messages.add(new Message("user", prompt));
        this.n = n;
    }

}
