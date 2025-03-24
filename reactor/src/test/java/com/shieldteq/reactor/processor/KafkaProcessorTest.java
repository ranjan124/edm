package com.shieldteq.reactor.processor;

import com.shieldteq.reactor.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Supplier;

@TestPropertySource(properties = {
        "spring.cloud.function.definition=processor;testProducer;testConsumer",
        "spring.cloud.stream.bindings.testConsumer-in-0.destination=output-topic",
        "spring.cloud.stream.bindings.testProducer-out-0.destination=input-topic"
})
class KafkaProcessorTest extends AbstractIntegrationTest {
    public static final Sinks.Many<String> reqSink = Sinks.many().unicast().onBackpressureBuffer();
    public static final Sinks.Many<String> resSink = Sinks.many().unicast().onBackpressureBuffer();
    private static final Logger log = LoggerFactory.getLogger(KafkaProcessorTest.class);

    @Test
    public void processorTest() {
        reqSink.tryEmitNext("hello");
        reqSink.tryEmitNext("world");

        resSink.asFlux()
                .take(2)
                .timeout(Duration.ofSeconds(5))
                .doOnNext(s -> log.info("Received: {}", s))
                .as(StepVerifier::create)
                .consumeNextWith(s -> Assertions.assertEquals("HELLO", s))
                .consumeNextWith(s -> Assertions.assertEquals("WORLD", s))
                .verifyComplete();
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public Supplier<Flux<String>> testProducer() {
            return reqSink::asFlux;
        }

        @Bean
        public Consumer<Flux<String>> testConsumer() {
            return f -> f.doOnNext(resSink::tryEmitNext).subscribe();
        }
    }
}
