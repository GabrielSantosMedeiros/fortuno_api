package com.example.fortuno_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.models.User;
import com.example.fortuno_api.services.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/personal-info")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) throws Exception {
        return ResponseEntity.ok().body(userService.getUser(username));

    }

    @PostMapping
    public ResponseEntity<Object> save(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PutMapping("/personal-info")
    public ResponseEntity<Object> modify(@ModelAttribute User newUserInfo) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.modifyUser(user.getUsername(), newUserInfo));
    }

    @DeleteMapping("/personal-info")
    public ResponseEntity<Object> delete() throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.delete(user.getUsername()));
    }
}
