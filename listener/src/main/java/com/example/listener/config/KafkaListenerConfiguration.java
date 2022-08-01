package com.example.listener.config;

import com.example.listener.KafkaListener;
import com.example.listener.config.properties.KafkaListenerProperties;
import com.example.listener.handler.ListenerHandler;
import com.example.listener.handler.ListenerHandlerImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableConfigurationProperties(KafkaListenerProperties.class)
public class KafkaListenerConfiguration {

    @Bean("kafkaConsumerFactory")
    public ConsumerFactory<String, byte[]> kafkaConsumerFactory(
         KafkaListenerProperties properties
    ) {
        return new DefaultKafkaConsumerFactory(
            properties.kafkaProperties.buildConsumerProperties(),
            new StringDeserializer(),
            new ByteArrayDeserializer()
        );
    }

    @Bean("kafkaListenerContainerFactory")
    @ConditionalOnBean(name = "kafkaConsumerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerContainerFactory(
            @Qualifier("kafkaConsumerFactory") ConsumerFactory<String, byte[]> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory containerFactory = new ConcurrentKafkaListenerContainerFactory<String, byte[]>();
        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }

    @Bean
    @ConditionalOnBean(name = "kafkaConsumerFactory")
    @ConditionalOnMissingBean
    public KafkaListener kafkaListener(
        ListenerHandler listenerHandler
    ) {
        return new KafkaListener(listenerHandler);
    }

    @Bean
    @ConditionalOnMissingBean
    public ListenerHandler listenerHandler() {
        return new ListenerHandlerImpl();
    }
}
