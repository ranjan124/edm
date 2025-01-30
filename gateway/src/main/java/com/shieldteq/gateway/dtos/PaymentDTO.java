package com.shieldteq.gateway.dtos;

record PaymentDTO(
        String id,
        String orderId,
        String customerId,
        Double amount
) {
}
