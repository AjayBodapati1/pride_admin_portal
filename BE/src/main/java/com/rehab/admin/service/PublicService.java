package com.rehab.admin.service;

import com.rehab.admin.execptions.EmailUserNotFoundException;
import com.rehab.admin.model.LoginCredentials;
import com.rehab.admin.model.User;
import com.rehab.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublicService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public String verify(LoginCredentials credentials) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        if(authentication.isAuthenticated()){
                return jwtService.generateToken(credentials.getEmail());
        }
        log.error("incorrect credentials");
        return "incorrect credentials";
    }

    public User registerUser(User user) {
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("{}'s registration successful", user.getName());
        return userRepository.save(user);
    }

    public String changePassword(String email, String newPassword) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(()-> new EmailUserNotFoundException("User not found with email: " + email));
        existingUser.setPassword(encoder.encode(newPassword));
        userRepository.save(existingUser);
        log.info("password update successful");
        return "Password update successful.";
    }
}
