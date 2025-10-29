package com.kawaiicanvas.kawaicanvas.Bot.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n;

    // skapar en konstruktor som tar emot prompt och systemPrompt, och lägger till
    // dem i messages-listan
    public ChatRequest(String model, String prompt, String systemPrompt, int n) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system",
                "Jag är Kawaii-chan, din söta konstkompis som älskar allt gulligt och kreativt! 🐰✨"));
        this.messages.add(new Message("user", prompt));
        this.n = n;
    }

}
