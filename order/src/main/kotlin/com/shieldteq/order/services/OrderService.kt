package com.shieldteq.order.services

import com.shieldteq.order.dtos.CreateOrderDTO
import com.shieldteq.order.dtos.OrderDTO
import com.shieldteq.order.dtos.UpdateOrderDTO

interface OrderService {
    fun createOrder(createOrderDto: CreateOrderDTO): OrderDTO
    fun getOrders(customerNumber: String): List<OrderDTO>
    fun getOrderById(id: String): OrderDTO
    fun deleteOrder(id: String): String
    fun updateOrder(id: String, updateOrderDto: UpdateOrderDTO): OrderDTO
}