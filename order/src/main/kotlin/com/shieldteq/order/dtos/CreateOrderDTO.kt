package com.shieldteq.order.dtos

data class CreateOrderDTO(
    val accountId: String,
    val items: List<ItemDTO>
)
