package com.example.fortuno_api.dtos.transaction;

import java.time.LocalDateTime;

public record TransactionDTO(
    double amount,
    String owner,
    String type,
    LocalDateTime createdAt,
    LocalDateTime lastModifiedAt
) {}
