package com.shieldteq.gateway.dtos;

record ItemDTO(
        String id,
        String name,
        String description,
        Double price,
        Integer quantity,
        String category
) {
}
