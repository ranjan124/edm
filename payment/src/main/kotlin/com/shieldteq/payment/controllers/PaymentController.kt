package com.shieldteq.payment.controllers

import com.shieldteq.payment.constants.Constants
import com.shieldteq.payment.dtos.CreatePaymentDTO
import com.shieldteq.payment.dtos.PaymentDTO
import com.shieldteq.payment.dtos.response.ResponseDTO
import com.shieldteq.payment.services.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payment")
class PaymentController(private val paymentService: PaymentService) {
    @GetMapping("/byId/{paymentId}")
    fun getPayment(@PathVariable paymentId: String): ResponseEntity<ResponseDTO<PaymentDTO>> {
        return ResponseEntity.ok(ResponseDTO(Constants.STATUS_200, Constants.STATUS_200_MESSAGE, paymentService.getPayment(paymentId)))
    }

    @GetMapping("/byOrderId/{orderId}")
    fun getPaymentsByOrderId(@PathVariable orderId: String): ResponseEntity<ResponseDTO<List<PaymentDTO>>> {
        return ResponseEntity.ok(ResponseDTO(Constants.STATUS_200, Constants.STATUS_200_MESSAGE, paymentService.getPaymentsByOrderId(orderId)))
    }

    @GetMapping("/byCustomerNumber/{customerNumber}")
    fun getPaymentsByCustomerId(@PathVariable customerNumber: String): ResponseEntity<ResponseDTO<List<PaymentDTO>>> {
        return ResponseEntity.ok(ResponseDTO(Constants.STATUS_200, Constants.STATUS_200_MESSAGE, paymentService.getPaymentsByCustomerNumber(customerNumber)))
    }

    @PostMapping
    fun createPayment(@RequestBody payment: CreatePaymentDTO): ResponseEntity<ResponseDTO<PaymentDTO>> {
        return ResponseEntity.ok(ResponseDTO(Constants.STATUS_201, Constants.STATUS_200_MESSAGE, paymentService.createPayment(payment)))
    }
}