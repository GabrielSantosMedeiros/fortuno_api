package com.example.fortuno_api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fortuno_api.dtos.UserPublicInfoDTO;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserPublicInfoDTO> getAllUsers() {
        List<User> usersList = userService.loadAllUsers();
        List<UserPublicInfoDTO> usersPublicInfo = new ArrayList<>();

        if(usersList!=null) {
            for(User user : usersList) {
                UserPublicInfoDTO userInfo = new UserPublicInfoDTO(
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getLastModifiedAt()
                );
                usersPublicInfo.add(userInfo);
            }
        }
        return usersPublicInfo;
    }

    @PostMapping
    public ResponseEntity<Object> save(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> modify(@PathVariable("username") String username, @ModelAttribute User newUserInfo) throws Exception {
        User user = (User) userService.loadUserByUsername(username);
        if(user==null) throw new Exception("user not exists.");
        if(newUserInfo.getUsername()!=null) userService.modifyUsername(username, newUserInfo.getUsername());
        UserPublicInfoDTO modifedUserJson = new UserPublicInfoDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getCreatedAt(), 
            user.getLastModifiedAt()
        );
        return ResponseEntity.ok().body(modifedUserJson);
    }
}
