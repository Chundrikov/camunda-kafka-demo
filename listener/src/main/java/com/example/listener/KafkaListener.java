package com.example.listener;

import com.example.listener.entity.TransactionEvent;
import com.example.listener.handler.ListenerHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.Payload;

public class KafkaListener {

    private ListenerHandler listenerHandler;

    public KafkaListener(ListenerHandler listenerHandler) {
        this.listenerHandler = listenerHandler;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    public void handleTransactionEvent(@Payload byte[] transactionEvent) {
        try{
            TransactionEvent event = objectMapper.readValue(
                    transactionEvent,
                    TransactionEvent.class
            );

            if(event != null) {
                listenerHandler.handleMessage(event);
            }
        } catch (Exception e) {
            System.out.println("Some problems during handling kafka transaction event");
        }
    }
}
