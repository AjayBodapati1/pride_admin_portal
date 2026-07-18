package com.rehab.admin.controller;

import com.rehab.admin.model.MailRequest;
import com.rehab.admin.model.User;
import com.rehab.admin.service.MailService;
import com.rehab.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/getStudents")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getStudents(), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user.toString());
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/editUser/{email}")
    public ResponseEntity<User> editUser(@PathVariable String email,@RequestBody User user) {
        User editedUser = userService.editUser(email, user);
        return new ResponseEntity<>(editedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployees")
    public ResponseEntity<List<User>> getEmployees(){
        return new ResponseEntity<>(userService.getEmployees(), HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody MailRequest mailRequest){
        return new ResponseEntity<>(mailService.sendEmail(mailRequest), HttpStatus.OK);
    }

}