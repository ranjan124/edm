package com.shieldteq.reactor.producer;

import com.shieldteq.reactor.AbstractIntegrationTest;
import com.shieldteq.reactor.common.MessageConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Consumer;

@TestPropertySource(properties = {
        "spring.cloud.function.definition=producer;testConsumer",
        "spring.cloud.stream.bindings.testConsumer-in-0.destination=input-topic"
})
class KafkaProducerWithKeyTest extends AbstractIntegrationTest {

    public static final Sinks.Many<Message<String>> sink = Sinks.many().unicast().onBackpressureBuffer();

    @Test
    void producerTest() {
        sink.asFlux()
                .map(MessageConverter::toRecord)
                .take(2)
                .timeout(Duration.ofSeconds(5))
                .as(StepVerifier::create)
                .consumeNextWith(s -> Assertions.assertEquals("msg 0", s.message()))
                .consumeNextWith(s -> Assertions.assertEquals("msg 1", s.message()))
                .verifyComplete();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public Consumer<Flux<Message<String>>> testConsumer() {
            return f -> f.doOnNext(sink::tryEmitNext).subscribe();
        }
    }
}
