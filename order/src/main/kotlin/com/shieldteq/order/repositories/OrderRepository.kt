package com.shieldteq.order.repositories

import com.shieldteq.order.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, String> {
    @Query("select o from Order o join fetch o.items where o.accountId = :accountId")
    fun findOrderByAccountId(accountId: String): List<Order>
}