package com.shieldteq.order.repositories

import com.shieldteq.order.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, String> {
    fun findOrderByAccountId(accountId: String): List<Order>
}