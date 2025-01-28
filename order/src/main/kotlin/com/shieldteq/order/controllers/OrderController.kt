package com.shieldteq.order.controllers

import com.shieldteq.inventory.constants.Constants
import com.shieldteq.order.dtos.CreateOrderDTO
import com.shieldteq.order.dtos.OrderDTO
import com.shieldteq.order.dtos.UpdateOrderDTO
import com.shieldteq.order.dtos.response.ResponseDTO
import com.shieldteq.order.services.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {
    @GetMapping("/all/{accountId}")
    fun getAllOrders(@PathVariable("accountId") accountId: String): ResponseEntity<ResponseDTO<List<OrderDTO>>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = orderService.getOrders(accountId)
        )
        return ResponseEntity.ok(resp)
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable("id") id: String): ResponseEntity<ResponseDTO<OrderDTO>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = orderService.getOrderById(id)

        )
        return ResponseEntity.ok(resp)
    }

    @PostMapping("/add")
    fun createOrder(@RequestBody createOrderDTO: CreateOrderDTO): ResponseEntity<ResponseDTO<OrderDTO>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_201,
            data = orderService.createOrder(createOrderDTO)
        )
        return ResponseEntity.ok(resp)
    }

    @PutMapping("/update/{id}")
    fun updateOrder(@PathVariable("id") id: String, @RequestBody updateOrderDTO: UpdateOrderDTO): ResponseEntity<ResponseDTO<OrderDTO>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = orderService.updateOrder(id, updateOrderDTO)
        )
        return ResponseEntity.ok(resp)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteOrder(@PathVariable("id") id: String): ResponseEntity<ResponseDTO<String>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = orderService.deleteOrder(id)
        )
        return ResponseEntity.ok(resp)
    }
}