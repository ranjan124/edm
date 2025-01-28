package com.shieldteq.inventory.services.impl

import com.shieldteq.inventory.dtos.AddItemDTO
import com.shieldteq.inventory.dtos.ItemDTO
import com.shieldteq.inventory.dtos.UpdateItemDTO
import com.shieldteq.inventory.entities.Item
import com.shieldteq.inventory.exceptions.ItemNotFoundException
import com.shieldteq.inventory.repositories.ItemRepository
import com.shieldteq.inventory.services.ItemService
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(private val itemRepository: ItemRepository) : ItemService {
    override fun addItem(addItemDTO: AddItemDTO): ItemDTO {
        val item = Item(
            name = addItemDTO.name,
            description = addItemDTO.description,
            quantity = addItemDTO.quantity,
            price = addItemDTO.price,
            category = addItemDTO.category
        )

        return itemRepository.save(item).let {
            ItemDTO(
                id = it.id!!,
                name = it.name,
                description = it.description,
                quantity = it.quantity,
                price = it.price,
                category = it.category
            )
        }
    }

    override fun updateItem(itemId: String, updateItemDTO: UpdateItemDTO): ItemDTO {
        val item: Item = itemRepository.findById(itemId)
            .orElseThrow { ItemNotFoundException("Item not found") }
            .copy(
                name = updateItemDTO.name,
                description = updateItemDTO.description,
                quantity = updateItemDTO.quantity,
                price = updateItemDTO.price,
                category = updateItemDTO.category
            )

        return itemRepository.save(item).let {
            ItemDTO(
                id = it.id!!,
                name = it.name,
                description = it.description,
                quantity = it.quantity,
                price = it.price,
                category = it.category
            )
        }
    }

    override fun getAllItems(): List<ItemDTO> = itemRepository.findAll().map {
        ItemDTO(
            id = it.id!!,
            name = it.name,
            description = it.description,
            quantity = it.quantity,
            price = it.price,
            category = it.category
        )
    }

    override fun getItemById(id: String): ItemDTO = itemRepository.findById(id)
        .orElseThrow { ItemNotFoundException("Item not found") }
        .let {
            ItemDTO(
                id = it.id!!,
                name = it.name,
                description = it.description,
                quantity = it.quantity,
                price = it.price,
                category = it.category
            )
        }

    override fun deleteItem(id: String): String {
        return itemRepository.findById(id)
            .orElseThrow { ItemNotFoundException("Item not found") }
            .let {
                itemRepository.delete(it)
                it.id ?: ""
            }
    }

}