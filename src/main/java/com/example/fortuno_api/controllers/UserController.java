package com.example.fortuno_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.dtos.user.LoginAuthenticationDTO;
import com.example.fortuno_api.dtos.user.UserPublicInfoDTO;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.security.TokenJWTService;
import com.example.fortuno_api.services.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenJWTService tokenService;

    @GetMapping
    public List<UserPublicInfoDTO> getAllUsers() {
        return userService.loadAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        User user = (User) userService.loadUserByUsername(username);
        UserPublicInfoDTO userJson = new UserPublicInfoDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getCreatedAt(), 
            user.getLastModifiedAt()
        );
        return ResponseEntity.ok().body(userJson);

    }

    @PostMapping
    public ResponseEntity<Object> save(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> modify(@PathVariable("username") String username, @ModelAttribute User newUserInfo) throws Exception {
        return ResponseEntity.ok().body(userService.modifyUser(username, newUserInfo));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> delete(@PathVariable("username") String username) throws Exception {
        return ResponseEntity.ok().body(userService.delete(username));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@ModelAttribute LoginAuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body("Token: " + token.toString());
    }
}
