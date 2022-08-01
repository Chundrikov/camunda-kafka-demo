package com.example.listener.config.properties;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConstructorBinding
@ConfigurationProperties(prefix = "com.example.demo.kafka.listener")
public class KafkaListenerProperties {

    private String topic;
    private String groupId;

    @NestedConfigurationProperty
    public KafkaProperties kafkaProperties;
}
