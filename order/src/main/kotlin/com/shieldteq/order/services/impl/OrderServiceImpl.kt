package com.shieldteq.order.services.impl

import com.shieldteq.order.dtos.CreateOrderDTO
import com.shieldteq.order.dtos.OrderDTO
import com.shieldteq.order.dtos.UpdateOrderDTO
import com.shieldteq.order.exceptions.OrderNotFoundException
import com.shieldteq.order.repositories.OrderRepository
import com.shieldteq.order.services.OrderService
import com.shieldteq.order.utils.toOrder
import com.shieldteq.order.utils.toOrderDTO
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository) : OrderService {
    override fun createOrder(createOrderDto: CreateOrderDTO): OrderDTO {
        return orderRepository.save(createOrderDto.toOrder()).toOrderDTO()
    }

    override fun getOrders(customerNumber: String): List<OrderDTO> {
        return orderRepository.findOrderByCustomerNumber(customerNumber).map { it.toOrderDTO() }
    }

    override fun getOrderById(id: String): OrderDTO {
        return orderRepository.findById(id).orElseThrow { OrderNotFoundException("Order $id not found") }.toOrderDTO()
    }

    override fun deleteOrder(id: String): String {
        orderRepository.findById(id).orElseThrow { OrderNotFoundException("Order $id not found") }.let(orderRepository::delete)
        return id
    }

    override fun updateOrder(id: String, updateOrderDto: UpdateOrderDTO): OrderDTO {
        return orderRepository.findById(id).orElseThrow { OrderNotFoundException("Order $id not found") }.let {
            orderRepository.save(it.copy(status = updateOrderDto.status)).toOrderDTO()
        }
    }
}


