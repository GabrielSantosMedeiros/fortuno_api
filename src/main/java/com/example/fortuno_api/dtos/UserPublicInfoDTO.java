package com.example.fortuno_api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPublicInfoDTO {
    
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
