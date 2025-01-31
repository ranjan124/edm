package com.shieldteq.payment.dtos

data class PaymentDTO(
    val id: String,
    val orderId: String,
    val customerNumber: String,
    val amount: Double
)
