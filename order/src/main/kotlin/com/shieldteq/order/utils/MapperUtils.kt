package com.shieldteq.order.utils

import com.shieldteq.order.dtos.CreateOrderDTO
import com.shieldteq.order.dtos.ItemDTO
import com.shieldteq.order.dtos.OrderDTO
import com.shieldteq.order.entities.Item
import com.shieldteq.order.entities.Order
import java.time.LocalDateTime


fun CreateOrderDTO.toOrder(): Order = Order(
    orderDate = LocalDateTime.now(),
    accountId = accountId,
    status = "CREATED",
    items = items.map { it.toItem() }.toSet()
)

fun ItemDTO.toItem(): Item {
    return Item(
        name = name,
        quantity = quantity,
        price = price,
        itemId = itemId
    )
}

fun Order.toOrderDTO(): OrderDTO {
    return OrderDTO(
        id = id!!,
        orderDate = orderDate,
        status = status,
        items = items.map { it.toItemDTO() }
    )
}

fun Item.toItemDTO(): ItemDTO {
    return ItemDTO(
        id = id,
        itemId = itemId,
        name = name,
        quantity = quantity,
        price = price
    )
}
