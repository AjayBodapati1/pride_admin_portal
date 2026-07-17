package com.rehab.admin.service;

import com.rehab.admin.model.User;
import com.rehab.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        String password = user.getName().toLowerCase().substring(0, 4) + user.getDateOfBirth().getYear();
        user.setActive(true);
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }

    public List<User> getStudents() {
        return userRepository.getStudents();
    }

    public User editUser(String email, User userDetails) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        existingUser.setName(userDetails.getName());
        existingUser.setDateOfBirth(userDetails.getDateOfBirth());
        existingUser.setFatherName(userDetails.getFatherName());
        existingUser.setMobileNumber(userDetails.getMobileNumber());
        existingUser.setDateOfJoining(userDetails.getDateOfJoining());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setRole(userDetails.getRole());
        existingUser.setActive(userDetails.getActive());

        return userRepository.save(existingUser);
    }

    public List<User> getEmployees() {
        return userRepository.getEmployees();
    }
}