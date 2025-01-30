package com.shieldteq.gateway.dtos;

public record PaymentDTO(
        String id,
        String orderId,
        String customerId,
        Double amount
) {
}
