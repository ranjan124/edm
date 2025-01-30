package com.shieldteq.gateway.dtos;

public record ItemDTO(
        String id,
        String name,
        String description,
        Double price,
        Integer quantity,
        String category
) {
}
