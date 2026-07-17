package com.rehab.admin.controller;

import com.rehab.admin.model.LoginCredentials;
import com.rehab.admin.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private PublicService publicService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials credentials){
        return new ResponseEntity<>(publicService.verify(credentials), HttpStatus.OK) ;
    }

}
