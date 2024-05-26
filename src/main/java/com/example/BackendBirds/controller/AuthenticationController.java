package com.example.BackendBirds.controller;

import com.example.BackendBirds.model.ApplicationUser;
import com.example.BackendBirds.DTO.LoginDTO;
import com.example.BackendBirds.DTO.LoginResponseDTO;
import com.example.BackendBirds.DTO.RegistrationDTO;
import com.example.BackendBirds.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        authenticationService.logoutUser(token);
        return ResponseEntity.ok().build();
    }
}
