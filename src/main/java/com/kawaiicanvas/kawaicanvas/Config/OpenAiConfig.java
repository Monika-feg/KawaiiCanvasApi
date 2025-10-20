package com.kawaiicanvas.kawaicanvas.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class OpenAiConfig {

    @Value("${app.openai.api.key}")
    private String apiKey;

    @Bean
    @Qualifier("openAiRestTemplate")
    public RestTemplate openaiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);

            return execution.execute(request, body);
        });
        return restTemplate;

    }

}
