package com.tulioanesio.Spring_Auth.model.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price) {
}
