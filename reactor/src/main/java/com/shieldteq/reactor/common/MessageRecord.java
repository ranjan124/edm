package com.shieldteq.reactor.common;

import lombok.Builder;
import reactor.kafka.receiver.ReceiverOffset;

@Builder
public record MessageRecord<T>(
        String key,
        T message,
        ReceiverOffset ack
) {
}
