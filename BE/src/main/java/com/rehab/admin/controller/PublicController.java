package com.rehab.admin.controller;

import com.rehab.admin.controller.dto.ChangePasswordRequestDTO;
import com.rehab.admin.controller.dto.LoginCredentialsDTO;
import com.rehab.admin.controller.mapper.CredentialsDtoMapper;
import com.rehab.admin.model.LoginCredentials;
import com.rehab.admin.model.User;
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

    @Autowired
    private CredentialsDtoMapper credentialsDtoMapper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentialsDTO credentialsDTO){
        LoginCredentials credentials = credentialsDtoMapper.toCredentialsEntity(credentialsDTO);
        return new ResponseEntity<>(publicService.verify(credentials), HttpStatus.OK) ;
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody User user){
//        return new ResponseEntity<>(publicService.registerUser(user), HttpStatus.OK) ;
//    }

    @PutMapping("changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequestDTO requestDTO){
        return new ResponseEntity<>(publicService.changePassword(requestDTO.email(), requestDTO.newPassword()), HttpStatus.OK);
    }

}
