package com.example.fortuno_api.dtos.user;

import java.time.LocalDateTime;

public record UserDTO(
    String username,
    String email,
    LocalDateTime craetedAt,
    LocalDateTime lastMofifiedAt
) {}
