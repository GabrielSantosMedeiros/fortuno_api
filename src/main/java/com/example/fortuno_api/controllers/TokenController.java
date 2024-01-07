package com.example.fortuno_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.dtos.user.LoginDTO;
import com.example.fortuno_api.dtos.user.TokenDTO;
import com.example.fortuno_api.models.User;

import com.example.fortuno_api.security.TokenJWTService;

@RestController
public class TokenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenJWTService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<Object> getToken(@ModelAttribute LoginDTO login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.username(), login.rawPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(new TokenDTO(token.toString()));
    } 
}
