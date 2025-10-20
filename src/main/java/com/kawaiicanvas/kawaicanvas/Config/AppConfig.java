package com.kawaiicanvas.kawaicanvas.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    private String openaiApiKey;
    private String openaiApiUrl;
    private String stripeApiKey;
    private String stripeSuccessUrl;
    private String stripeCancelUrl;
    private String adminUsername;
    private String adminPassword;

}
