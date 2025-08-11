package com.tulioanesio.Spring_Auth.model.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationDTO(
        @NotBlank(message = "Email is required")
        @Email
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 6)
        String password) {
}

