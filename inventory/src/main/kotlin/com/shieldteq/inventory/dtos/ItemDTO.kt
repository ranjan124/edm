package com.shieldteq.inventory.dtos

data class ItemDTO(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int = 0,
    val category: String
)
