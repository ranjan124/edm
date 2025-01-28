package com.shieldteq.order.dtos.response

data class ResponseDTO<T>(
    val status: String,
    val message: String,
    val data: T
)
