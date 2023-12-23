package com.example.fortuno_api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.dtos.UserPublicInfoDTO;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;

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
                    user.getCreatedAt(),
                    user.getLastModifiedAt()
                );
                usersPublicInfo.add(userInfo);
            }
        }
        return usersPublicInfo;
    }
}
