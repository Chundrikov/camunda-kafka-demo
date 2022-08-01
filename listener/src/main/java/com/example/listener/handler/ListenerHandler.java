package com.example.listener.handler;

import com.example.listener.entity.TransactionEvent;

public interface ListenerHandler {

    public String handleMessage(TransactionEvent transactionEvent);
}
