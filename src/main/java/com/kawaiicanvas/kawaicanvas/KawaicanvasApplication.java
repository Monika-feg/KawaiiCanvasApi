package com.kawaiicanvas.kawaicanvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class KawaicanvasApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("MONGO_DB_URI", dotenv.get("MONGO_DB_URI"));
		System.setProperty("AI_KEY", dotenv.get("AI_KEY"));
		System.setProperty("OPENAI_API_URL", dotenv.get("OPENAI_API_URL"));
		SpringApplication.run(KawaicanvasApplication.class, args);
	}

}
