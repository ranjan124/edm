package com.shieldteq.order.dtos

data class UpdateOrderDTO(
    val status: String,
    val addItems: List<ItemDTO>,
    val removeItems: List<OrderDTO>
)
