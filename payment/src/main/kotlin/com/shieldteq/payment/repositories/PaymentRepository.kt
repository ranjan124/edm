package com.shieldteq.payment.repositories

import com.shieldteq.payment.entities.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, String> {
    fun findAllByOrderId(orderId: String): List<Payment>
    fun findAllByCustomerId(customerId: String): List<Payment>
}