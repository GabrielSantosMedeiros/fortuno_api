package com.example.fortuno_api.dtos.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPublicInfoDTO {
    
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
