package com.shieldteq.customer.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@Schema(
        name = "Customer Create Input",
        description = "Input type for creating a new customer"
)
public record CustomerCreateDTO(
        @Schema(description = "Customer ID in the format UUID", example = "a0b1c2d3-4e5f-6789-0a1b-2c3d4e5f6789")
        String id,

        @Schema(description = "Customer number numeric 7 digit or more", example = "1234567 | 123456769")
        @NotBlank(message = "Name cannot be null or empty")
        String name,

        @Schema(description = "Customer email", example = "2X4d3@example.com")
        @NotBlank(message = "Email cannot be null or empty")
        @Email(message = "Email is not valid")
        String email,

        @Schema(description = "Customer phone number in the format (+49|0) 1234567", example = "+1 1234567 | 0 1234567")
        @NotBlank(message = "Phone cannot be null or empty")
        @Pattern(regexp = "^(\\+?\\d{1,3}|0)\\d{5,9}$", message = "Phone number is not valid")
        String phone
) {
}

