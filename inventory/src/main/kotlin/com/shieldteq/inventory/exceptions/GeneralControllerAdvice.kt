package com.shieldteq.inventory.exceptions

import com.shieldteq.inventory.dtos.response.ErrorResponseDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class GeneralControllerAdvice : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any> {
        val validationErrors = ex.bindingResult.allErrors.filterIsInstance<FieldError>().associate { it.field to it.defaultMessage }
        return ResponseEntity(validationErrors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(e: ItemNotFoundException, w: WebRequest): ErrorResponseDTO {
        return ErrorResponseDTO(w.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "Contact admin for more details", LocalDateTime.now())
    }

    @ExceptionHandler(ItemNotFoundException::class)
    fun handleItemNotFoundException(e: ItemNotFoundException, w: WebRequest): ErrorResponseDTO {
        return ErrorResponseDTO(w.getDescription(false), HttpStatus.BAD_REQUEST, e.message ?: "Contact admin for more details", LocalDateTime.now())
    }
}