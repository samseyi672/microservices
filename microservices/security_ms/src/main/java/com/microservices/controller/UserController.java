package com.activedge.controller;


import com.activedge.dto.AuthRequest;
import com.activedge.dto.UserDto;
import com.activedge.model.User;
import com.activedge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class UserController {
    @Autowired
    private UserService service;

    @Value("${api.version}")
    private String apiVersion ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody UserDto user) {
        User  currentUser  = service.saveUser(user) ;
        try {
            // Build a created response
            return ResponseEntity
                    .created(new URI("/product/" + currentUser.getId()))
                    .eTag(apiVersion)
                    .body(currentUser);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestParam("token") String token) {
        try {
            // Build a created response
            service.validateUser(token);
            return ResponseEntity
                   .created(new URI("/validate"))
                    .eTag(apiVersion)
                    .body("Token is valid");
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
