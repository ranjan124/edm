package com.shieldteq.gateway.dtos;

import java.time.LocalDateTime;
import java.util.List;

record OrderDTO(
        String id,
        LocalDateTime orderDate,
        String status,
        List<ItemDTO> items
) {
}