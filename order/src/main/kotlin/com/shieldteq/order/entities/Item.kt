package com.shieldteq.order.entities

data class Item(
    val id: Long,
    val name: String,
    val price: Double,
    val quantity: Int,
    val orderId: String
)
