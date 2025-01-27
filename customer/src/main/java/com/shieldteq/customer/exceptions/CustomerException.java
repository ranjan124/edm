package com.shieldteq.customer.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerException extends RuntimeException {
    private final String path;

    public CustomerException(String message, String path) {
        super(message);
        this.path = path;
    }
}
