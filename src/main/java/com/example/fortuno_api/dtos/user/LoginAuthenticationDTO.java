package com.example.fortuno_api.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginAuthenticationDTO {
    
    private String username;
    private String password;
}
