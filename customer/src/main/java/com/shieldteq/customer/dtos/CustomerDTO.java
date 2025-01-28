package com.shieldteq.customer.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Customer",
        description = "Customer details"
)
public record CustomerDTO(
        @Schema(description = "Customer ID in the format UUID", example = "a0b1c2d3-4e5f-6789-0a1b-2c3d4e5f6789")
        String id,
        @Schema(description = "Customer number numeric 7 digit or more", example = "1234567 | 123456769")
        String customerNumber,
        @Schema(description = "Customer name", example = "John Doe")
        String name,
        @Schema(description = "Customer email", example = "2X4d3@example.com")
        String email,
        @Schema(description = "Customer phone number in the format (+49|0) 1234567", example = "+1 1234567 | 0 1234567")
        String phone
) {
}
