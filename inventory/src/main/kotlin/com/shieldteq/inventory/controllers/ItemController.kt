package com.shieldteq.inventory.controllers

import com.shieldteq.inventory.constants.Constants
import com.shieldteq.inventory.dtos.AddItemDTO
import com.shieldteq.inventory.dtos.ItemDTO
import com.shieldteq.inventory.dtos.UpdateItemDTO
import com.shieldteq.inventory.dtos.response.ResponseDTO
import com.shieldteq.inventory.services.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService
) {
    @GetMapping
    fun getAllItems(): ResponseEntity<ResponseDTO<List<ItemDTO>>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = itemService.getAllItems()

        )
        return ResponseEntity.ok(resp)
    }

    @GetMapping("/{id}")
    fun getItemById(@PathVariable("id") id: String): ResponseEntity<ResponseDTO<ItemDTO>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = itemService.getItemById(id)

        )
        return ResponseEntity.ok(resp)
    }

    @PostMapping("/add")
    fun addItem(@RequestBody addItemDTO: AddItemDTO): ResponseEntity<ResponseDTO<ItemDTO>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_201,
            data = itemService.addItem(addItemDTO)

        )
        return ResponseEntity.ok(resp)
    }

    @PutMapping("/update/{id}")
    fun updateItem(@PathVariable("id") id: String, @RequestBody updateItemDTO: UpdateItemDTO): ResponseEntity<ResponseDTO<ItemDTO>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = itemService.updateItem(id, updateItemDTO)

        )
        return ResponseEntity.ok(resp)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteItem(@PathVariable("id") id: String): ResponseEntity<ResponseDTO<String>> {
        val resp = ResponseDTO(
            message = Constants.STATUS_200_MESSAGE,
            status = Constants.STATUS_200,
            data = itemService.deleteItem(id)

        )
        return ResponseEntity.ok(resp)
    }
}