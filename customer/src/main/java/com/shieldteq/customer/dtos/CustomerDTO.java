package com.shieldteq.customer.dtos;

public record CustomerDTO(
    String id,
    String customerNumber,
    String name,
    String email,
    String phone
) {
}
