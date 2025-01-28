package com.shieldteq.order.utils

import com.shieldteq.order.dtos.CreateOrderDTO
import com.shieldteq.order.dtos.OrderDTO
import com.shieldteq.order.entities.Order
import java.time.LocalDateTime


fun CreateOrderDTO.toOrder(): Order = Order(
    orderDate = LocalDateTime.now(),
    accountId = accountId,
    status = "CREATED"
)

fun Order.toOrderDTO(): OrderDTO {
    return OrderDTO(
        id = id!!,
        orderDate = orderDate,
        status = status,
        items = listOf()
    )
}