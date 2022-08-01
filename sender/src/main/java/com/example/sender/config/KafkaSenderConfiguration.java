package com.example.sender.config;

import com.example.sender.KafkaEventSender;
import com.example.sender.config.properties.KafkaSenderProperties;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaSenderProperties.class)
public class KafkaSenderConfiguration {

    @Bean("kafkaEventSender")
    public ReactiveKafkaProducerTemplate<String, byte[]> kafkaEventSender(
            KafkaSenderProperties senderProperties
    ) {
        Map<String, Object> properties = senderProperties.kafkaProperties.buildProducerProperties();
        SenderOptions<String, byte[]> senderOptions = SenderOptions.<String, byte[]>create(properties)
                .withKeySerializer(new StringSerializer())
                .withValueSerializer(new ByteArraySerializer());
        ReactiveKafkaProducerTemplate<String, byte[]> reactiveKafkaProducerTemplate =
                new ReactiveKafkaProducerTemplate<>(senderOptions);
        return reactiveKafkaProducerTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public KafkaEventSender sender(KafkaSenderProperties properties,
                                   @Qualifier("kafkaEventSender")
                                   ReactiveKafkaProducerTemplate<String, byte[]> kafkaSender) {
        return new KafkaEventSender(properties, kafkaSender);
    }
}
