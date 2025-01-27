package com.shieldteq.customer.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private String status;
    private String message;
    private T data;
}
