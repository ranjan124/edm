package com.shieldteq.inventory.services

import com.shieldteq.inventory.dtos.AddItemDTO
import com.shieldteq.inventory.dtos.ItemDTO
import com.shieldteq.inventory.dtos.UpdateItemDTO

interface ItemService {
    fun addItem(addItemDTO: AddItemDTO): ItemDTO
    fun updateItem(itemId: String, updateItemDTO: UpdateItemDTO): ItemDTO
    fun getAllItems(): List<ItemDTO>
    fun getItemById(id: String): ItemDTO
    fun deleteItem(id: String): String
}