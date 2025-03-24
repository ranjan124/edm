package com.shieldteq.reactor.consumer;

import com.shieldteq.reactor.common.MessageConverter;
import com.shieldteq.reactor.common.MessageRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {
    public static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Bean
    public Consumer<Flux<Message<String>>> consumer() {
        return flux -> flux
                .map(MessageConverter::toRecord)
                .doOnNext(this::printMessageDetails)
                .subscribe();
    }

    private void printMessageDetails(MessageRecord<String> record) {
        log.info("Payload: {}", record.message());
        log.info("Key: {}", record.key());
        record.ack().acknowledge();
    }

}
