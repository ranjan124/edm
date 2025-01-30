package com.shieldteq.gateway.dtos;

record Summary(
        CustomerDTO customerDTO,
        OrderDTO orderDTO,
        PaymentDTO paymentDTO
) {
}