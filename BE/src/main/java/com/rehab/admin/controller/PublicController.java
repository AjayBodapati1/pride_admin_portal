package com.rehab.admin.controller;

import com.rehab.admin.model.User;
import com.rehab.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/getEmployees")
    public ResponseEntity<List<User>> getEmployees(){
        return new ResponseEntity<>(userService.getEmployees(), HttpStatus.OK);
    }
}
