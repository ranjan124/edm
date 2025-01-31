package com.shieldteq.order.dtos

data class CreateOrderDTO(
    val customerNumber: String,
    val items: List<ItemDTO>
)
