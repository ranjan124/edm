package com.shieldteq.customer.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Response type when an error occurred"
)
public record ErrorResponseDTO(
        @Schema(description = "Path of the request", example = "/api/v1/customers")
        String path,
        @Schema(description = "Error code", example = "400")
        HttpStatus errorCode,
        @Schema(description = "Error message", example = "Bad Request")
        String message,
        @Schema(description = "Error timestamp", example = "2023-01-01T00:00:00")
        LocalDateTime time
) {
}
