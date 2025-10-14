package com.kawaiicanvas.kawaicanvas.Bot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// en klass som representerar svaret från chat API:et
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {

        private Message message;
    }

}
