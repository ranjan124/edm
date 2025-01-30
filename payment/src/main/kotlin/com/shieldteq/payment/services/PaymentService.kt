package com.shieldteq.payment.services

import com.shieldteq.payment.dtos.CreatePaymentDTO
import com.shieldteq.payment.dtos.PaymentDTO

interface PaymentService {
    fun createPayment(payment: CreatePaymentDTO): PaymentDTO
    fun getPayment(id: String): PaymentDTO
    fun getPaymentsByOrderId(orderId: String): List<PaymentDTO>
    fun getPaymentsByCustomerId(customerId: String): List<PaymentDTO>
}