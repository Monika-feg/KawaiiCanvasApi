package com.kawaiicanvas.kawaicanvas.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

// tagit inspiration av denna https://www.youtube.com/watch?v=GNU-S6pUu1c
@Configuration
public class DatabaseLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public DatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Skapa admin i databasen om den inte redan finns
    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            // Kolla om admin-användaren redan finns
            // anars skapas en med krypterat lösenord
            if (userRepository.findByUserName(adminUsername).isEmpty()) {
                String encodedPassword = passwordEncoder.encode(adminPassword); // Koda lösenordet
                User adminUser = new User(null, adminUsername, encodedPassword, UserRoleEnum.ADMIN);
                userRepository.save(adminUser);
                System.out.println("Admin user created successfully with username: " + adminUsername);
            } else {
                System.out.println("Admin user already exists");
            }
        };
    }
}
