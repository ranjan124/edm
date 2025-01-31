package com.shieldteq.payment.dtos

data class CreatePaymentDTO(
    val orderId: String,
    val customerNumber: String,
    val amount: Double
)