package com.example.sender;

import com.example.sender.config.properties.KafkaSenderProperties;
import com.example.sender.entity.TransactionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

public class KafkaEventSender {

    private KafkaSenderProperties senderProperties;

    private ReactiveKafkaProducerTemplate<String, byte[]> reactiveKafkaSender;

    private ObjectMapper objectMapper = new ObjectMapper();

    public KafkaEventSender(KafkaSenderProperties senderProperties, ReactiveKafkaProducerTemplate<String, byte[]> reactiveKafkaSender) {
        this.senderProperties = senderProperties;
        this.reactiveKafkaSender = reactiveKafkaSender;
    }

    public void sendEvent(TransactionEvent event) throws JsonProcessingException {
        reactiveKafkaSender.send(senderProperties.topic, objectMapper.writeValueAsBytes(event));
    }
}
