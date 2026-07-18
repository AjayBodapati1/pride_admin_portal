package com.rehab.admin.service;

import com.rehab.admin.model.LoginCredentials;
import com.rehab.admin.model.User;
import com.rehab.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
        return "incorrect credentials";
    }

    public User registerUser(User user) {
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
