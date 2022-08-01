package com.example.sender.config.properties;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConstructorBinding
@ConfigurationProperties("com.example.demo.kafka.sender")
public class KafkaSenderProperties {

    public String topic;

    @NestedConfigurationProperty
    public KafkaProperties kafkaProperties;
}
