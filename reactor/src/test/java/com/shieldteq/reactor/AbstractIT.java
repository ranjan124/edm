package com.shieldteq.reactor;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        topics = {"order-events"},
        bootstrapServersProperty = "spring.kafka.bootstrapServers"
)
public class AbstractIT {
    @Autowired
    private EmbeddedKafkaBroker broker;

    protected <V> KafkaReceiver<String, V> createReceiver(String... topics) {
        return createReceiver(options ->
                options.withKeyDeserializer(new StringDeserializer())
                        .withValueDeserializer(new JsonDeserializer<V>().trustedPackages("*"))
                        .subscription(List.of(topics))
        );
    }

    protected <K, V> KafkaReceiver<K, V> createReceiver(UnaryOperator<ReceiverOptions<K, V>> builder) {
        Map<String, Object> props = KafkaTestUtils.consumerProps("test-group", "true", broker);
        ReceiverOptions<K, V> options = ReceiverOptions.<K, V>create(props);
        options = builder.apply(options);
        return KafkaReceiver.create(options);
    }
}
