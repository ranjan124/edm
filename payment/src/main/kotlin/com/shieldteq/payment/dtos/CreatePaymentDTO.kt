package com.shieldteq.payment.dtos

data class CreatePaymentDTO(
    val orderId: String,
    val customerId: String,
    val amount: Double
)