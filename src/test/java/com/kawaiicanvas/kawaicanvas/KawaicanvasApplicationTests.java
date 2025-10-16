package com.kawaiicanvas.kawaicanvas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"AI_KEY=test-key-for-testing",
		"openai.api.key=test-key-for-testing",
		"OPENAI_API_URL=https://api.openai.com/v1/chat/completions",
		"MONGO_DB_URI=mongodb://localhost:27017/test",
		"CLOUDINARY_CLOUD_NAME=test-cloud",
		"CLOUDINARY_API_KEY=test-api-key",
		"CLOUDINARY_API_SECRET=test-api-secret",
		"STRIPE_API_KEY=test-stripe-key",
		"STRIPE_SUCCESS_URL=http://localhost:3000/success",
		"STRIPE_CANCEL_URL=http://localhost:3000/cancel",
		// Använd embedded MongoDB för tester
		"spring.data.mongodb.database=test-db"
})
class KawaicanvasApplicationTests {

	@Test
	void contextLoads() {
	}

}
