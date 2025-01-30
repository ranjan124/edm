package com.shieldteq.payment.dtos.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponseDTO(
    val path: String,
    val status: HttpStatus,
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
