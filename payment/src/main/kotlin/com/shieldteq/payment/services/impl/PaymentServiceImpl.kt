package com.shieldteq.payment.services.impl

import com.shieldteq.payment.dtos.CreatePaymentDTO
import com.shieldteq.payment.dtos.PaymentDTO
import com.shieldteq.payment.exceptions.PaymentNotFoundException
import com.shieldteq.payment.repositories.PaymentRepository
import com.shieldteq.payment.services.PaymentService
import com.shieldteq.payment.utils.toPayment
import com.shieldteq.payment.utils.toPaymentDTO
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl(private val paymentRepository: PaymentRepository) : PaymentService {
    override fun createPayment(payment: CreatePaymentDTO): PaymentDTO {
        return payment.toPayment().let { paymentRepository.save(it).toPaymentDTO() }
    }

    override fun getPayment(id: String): PaymentDTO {
        return paymentRepository.findById(id).orElseThrow { PaymentNotFoundException("Payment not found") }.toPaymentDTO()
    }

    override fun getPaymentsByOrderId(orderId: String): List<PaymentDTO> {
        return paymentRepository.findAllByOrderId(orderId).map { it.toPaymentDTO() }
    }

    override fun getPaymentsByCustomerNumber(customerId: String): List<PaymentDTO> {
        return paymentRepository.findAllByCustomerNumber(customerId).map { it.toPaymentDTO() }
    }
}