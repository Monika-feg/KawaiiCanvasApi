package com.kawaiicanvas.kawaicanvas.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.springframework.security.core.userdetails.User.withUsername;

// tagit inspiration från denna https://www.geeksforgeeks.org/advance-java/spring-security-at-method-level/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // en metod som bara admin kan anropa
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminData() {
        return userRepository.findByUserName("admin").toString();
    }

    // laddar användare baserat på användarnamn för autentisering
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).stream().findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

}
