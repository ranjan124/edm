package com.shieldteq.reactor.consumer;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DummyOrderEvent(
        UUID orderId,
        long customerId
) {
}
