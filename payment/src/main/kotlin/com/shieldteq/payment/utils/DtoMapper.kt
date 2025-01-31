package com.shieldteq.payment.utils

import com.shieldteq.payment.dtos.CreatePaymentDTO
import com.shieldteq.payment.dtos.PaymentDTO
import com.shieldteq.payment.entities.Payment

fun Payment.toPaymentDTO(): PaymentDTO {
    return PaymentDTO(
        id = id!!,
        orderId = orderId,
        customerNumber = customerNumber,
        amount = amount
    )
}

fun CreatePaymentDTO.toPayment(): Payment {
    return Payment(
        orderId = orderId,
        customerNumber = customerNumber,
        amount = amount
    )
}