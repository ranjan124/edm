package com.shieldteq.reactor.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerRunner implements CommandLineRunner {
    private final ReactiveKafkaProducerTemplate<String, OrderEvent> template;

    @Override
    public void run(String... args) throws Exception {
        this.orderEventsFlux()
                .flatMap(oe -> this.template.send("order-events", oe.orderId().toString(), oe))
                .doOnNext(r -> log.info("Order events sent to Kafka: {}", r.recordMetadata()))
                .subscribe();
    }

    private Flux<OrderEvent> orderEventsFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(1000)
                .map(i-> OrderEvent.builder()
                        .orderId(UUID.randomUUID())
                        .customerId(i)
                        .orderDate(LocalDateTime.now())
                        .build());

    }
}
