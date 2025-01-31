package com.shieldteq.payment.entities

import jakarta.persistence.*

@Entity
@Table(name = "payment")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val orderId: String,
    val customerNumber: String,
    val amount: Double
) : BaseEntity()
