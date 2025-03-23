package com.shieldteq.reactor.producer;

import com.shieldteq.reactor.AbstractIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.test.StepVerifier;

import java.time.Duration;


class ProducerRunnerTest extends AbstractIT {
    private static final Logger log = LoggerFactory.getLogger(ProducerRunnerTest.class);

    @Test
    public void producerRunnerTest() {
        KafkaReceiver<String, OrderEvent> receiver = createReceiver("order-events");
        Flux<ReceiverRecord<String, OrderEvent>> events = receiver.receive()
                .take(10)
                .doOnNext(r -> log.info("key: {}, value: {}", r.key(), r.value()));

        StepVerifier.create(events)
                .consumeNextWith(r -> Assertions.assertNotNull(r.value().orderId()))
                .expectNextCount(9)
                .expectComplete()
                .verify(Duration.ofSeconds(10));

    }
}
