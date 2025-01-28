package com.shieldteq.inventory.repositories

import com.shieldteq.inventory.entities.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, String> {
}