package com.example.listener.handler;

import com.example.listener.entity.Result;
import com.example.listener.entity.TransactionEvent;
import org.springframework.stereotype.Component;

@Component
public class ListenerHandlerImpl implements ListenerHandler {
    @Override
    public String handleMessage(TransactionEvent transactionEvent) {
        return Result.SUCCESS.name();
    }
}
