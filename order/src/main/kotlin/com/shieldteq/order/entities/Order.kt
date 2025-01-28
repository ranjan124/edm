package com.shieldteq.order.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val orderDate: LocalDateTime,
    val accountId: String,
    val status: String
) : BaseEntity()
