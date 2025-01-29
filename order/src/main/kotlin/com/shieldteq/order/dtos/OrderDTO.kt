package com.shieldteq.order.dtos

import java.time.LocalDateTime

data class OrderDTO(
    val id: String,
    val orderDate: LocalDateTime,
    val status: String,
    var items: List<ItemDTO>
)
