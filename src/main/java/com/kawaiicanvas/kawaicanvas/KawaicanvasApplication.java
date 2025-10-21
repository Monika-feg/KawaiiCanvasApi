package com.kawaiicanvas.kawaicanvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class KawaicanvasApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MONGO_DB_URI", dotenv.get("MONGO_DB_URI"));
		System.setProperty("AI_KEY", dotenv.get("AI_KEY"));
		System.setProperty("OPENAI_API_URL", dotenv.get("OPENAI_API_URL"));
		System.setProperty("STRIPE_API_KEY", dotenv.get("STRIPE_API_KEY"));
		System.setProperty("STRIPE_SUCCESS_URL", dotenv.get("STRIPE_SUCCESS_URL"));
		System.setProperty("STRIPE_CANCEL_URL", dotenv.get("STRIPE_CANCEL_URL"));
		System.setProperty("ADMIN_USERNAME", dotenv.get("ADMIN_USERNAME"));
		System.setProperty("ADMIN_PASSWORD", dotenv.get("ADMIN_PASSWORD"));
		SpringApplication.run(KawaicanvasApplication.class, args);
	}

}
