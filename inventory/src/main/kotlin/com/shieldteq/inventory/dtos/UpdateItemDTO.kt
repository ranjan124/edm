package com.shieldteq.inventory.dtos

data class UpdateItemDTO(
    val name: String,
    val description: String,
    val quantity: Int = 0,
    val price: Double = 0.0,
    val category: String
)
