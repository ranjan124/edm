package com.shieldteq.order.entities

import jakarta.persistence.*

@Entity
@Table(name = "items")
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val itemId: String,
    val name: String,
    val price: Double,
    val quantity: Int)
