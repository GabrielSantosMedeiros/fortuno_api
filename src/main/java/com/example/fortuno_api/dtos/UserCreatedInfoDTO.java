package com.example.fortuno_api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreatedInfoDTO {
    
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
