package com.shieldteq.reactor.consumer;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ReceiverOptions<String, DummyOrderEvent> receiverOptions(KafkaProperties kafkaProperties) {
        return ReceiverOptions.<String, DummyOrderEvent>create(kafkaProperties.buildConsumerProperties())
                .consumerProperty(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, false)
                .consumerProperty(JsonDeserializer.USE_TYPE_INFO_HEADERS, false)
                .consumerProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, DummyOrderEvent.class)
                .subscription(List.of("order-events"));

    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, DummyOrderEvent> consumerTemplate(ReceiverOptions<String, DummyOrderEvent> options) {
        return new ReactiveKafkaConsumerTemplate<>(options);
    }
}
