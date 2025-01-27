package com.shieldteq.customer.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String path;
    private HttpStatus errorCode;
    private String message;
    private LocalDateTime time;
}
