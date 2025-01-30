package com.shieldteq.payment.dtos.response

data class ResponseDTO<T>(
    val status: String,
    val message: String,
    val data: T
)
