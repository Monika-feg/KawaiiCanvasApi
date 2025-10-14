package com.kawaiicanvas.kawaicanvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class KawaicanvasApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("MONGO_DB_URI", dotenv.get("MONGO_DB_URI"));
		SpringApplication.run(KawaicanvasApplication.class, args);
	}

}
