package com.shieldteq.reactor.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerRunner implements CommandLineRunner {
    private final ReactiveKafkaConsumerTemplate<String, DummyOrderEvent> template;

    @Override
    public void run(String... args) throws Exception {
        this.template.receive()
                .doOnNext(r -> log.info("key: {}, value: {}", r.key(), r.value()))
                .doOnError(t -> log.error("error: ", t))
                .subscribe();
    }
}
