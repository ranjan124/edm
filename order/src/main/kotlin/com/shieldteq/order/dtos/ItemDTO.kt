package com.shieldteq.order.dtos

data class ItemDTO(
    val id: Long? = null,
    val itemId: String,
    val name: String,
    val price: Double,
    val quantity: Int
)
