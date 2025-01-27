package com.shieldteq.customer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerUpdateDTO(
        @NotBlank(message = "Name cannot be null or empty")
        String name,
        @NotBlank(message = "Email cannot be null or empty")
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Phone cannot be null or empty")
        @Pattern(regexp = "^(\\+?\\d{1,3}|0)\\d{5,9}$", message = "Phone number is not valid")
        String phone) {
}
