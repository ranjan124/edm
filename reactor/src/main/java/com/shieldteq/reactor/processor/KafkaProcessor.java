package com.shieldteq.reactor.processor;

import com.shieldteq.reactor.common.MessageConverter;
import com.shieldteq.reactor.common.MessageRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverOffset;

import java.util.function.Function;

@Slf4j
@Configuration
public class KafkaProcessor {
    @Bean
    public Function<Flux<Message<String>>, Flux<Message<String>>> processor() {
        return flux -> flux
                .doOnNext(s -> log.info("processor received {}", s))
                .concatMap(this::process)
                .doOnNext(s -> log.info("processed {}", s));
    }

    private Mono<Message<String>> process(Message<String> msg) {
        return Mono.just(msg).map(this::toMessage);
    }

    private Message<String> toMessage(Message<String> msg) {
        MessageRecord<String> record = MessageConverter.toRecord(msg);
        record.ack().acknowledge();
        return MessageBuilder.withPayload(record.message())
                .setHeader(KafkaHeaders.KEY, record.key())
                .build();
    }
}
