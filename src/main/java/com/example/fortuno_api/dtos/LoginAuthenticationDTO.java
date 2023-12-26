package com.example.fortuno_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginAuthenticationDTO {
    
    private String username;
    private String password;
}
