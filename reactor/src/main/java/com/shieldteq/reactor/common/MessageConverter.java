package com.shieldteq.reactor.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import reactor.kafka.receiver.ReceiverOffset;

@Slf4j
public class MessageConverter {
    private MessageConverter() {
    }

    public static <T> MessageRecord<T> toRecord(Message<T> message) {
        log.info("Message produced {}", message);
        return MessageRecord.<T>builder()
                .message(message.getPayload())
                .key(message.getHeaders().get(KafkaHeaders.RECEIVED_KEY, String.class))
                .ack(message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, ReceiverOffset.class))
                .build();
    }
}
