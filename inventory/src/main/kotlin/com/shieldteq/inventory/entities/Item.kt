package com.shieldteq.inventory.entities

import jakarta.persistence.*

@Entity
@Table(name = "items")
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int = 0,
    val category: String
) : BaseEntity()
