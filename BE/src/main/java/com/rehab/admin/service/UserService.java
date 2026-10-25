package com.rehab.admin.service;

import com.rehab.admin.execptions.EmailUserNotFoundException;
import com.rehab.admin.model.User;
import com.rehab.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        String password = user.getName().toLowerCase().substring(0, 4) + user.getDateOfBirth().getYear();
        user.setActive(true);
        user.setPassword(encoder.encode(password));
        log.info("user {} creation successful", user.getName());
        return userRepository.save(user);
    }

    public List<User> getStudents() {
        log.info("listing all students");
        return userRepository.getStudents();
    }

    public User editUser(String email, User userDetails) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailUserNotFoundException("User not found with email: " + email));

        existingUser.setName(userDetails.getName());
        existingUser.setDateOfBirth(userDetails.getDateOfBirth());
        existingUser.setFatherName(userDetails.getFatherName());
        existingUser.setMobileNumber(userDetails.getMobileNumber());
        existingUser.setDateOfJoining(userDetails.getDateOfJoining());
        existingUser.setRole(userDetails.getRole());
        existingUser.setActive(userDetails.getActive());

        log.info("user {} edit successful", existingUser.getName());
        return userRepository.save(existingUser);
    }

    public List<User> getEmployees() {
        log.info("listing all employees");
        return userRepository.getEmployees();
    }

    public List<User> getActiveStudents() {
        log.info("listing all active students");
        return userRepository.getActiveStudents();
    }

    public List<User> getActiveEmployees() {
        log.info("listing all active employees");
        return userRepository.getActiveEmployees();
    }

    public List<User> getAll() {
        log.info("listing all users");
        return userRepository.getAll();
    }
}