package com.shieldteq.reactor.producer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OrderEvent(
        UUID orderId,
        long customerId,
        LocalDateTime orderDate
) {
}
