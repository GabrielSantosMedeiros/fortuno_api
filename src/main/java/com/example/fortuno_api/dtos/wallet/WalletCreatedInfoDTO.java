package com.example.fortuno_api.dtos.wallet;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalletCreatedInfoDTO {
    
    private double balance;
    private String name;
    private String description;
    private String owner;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
