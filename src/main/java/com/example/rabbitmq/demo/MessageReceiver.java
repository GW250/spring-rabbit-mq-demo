package com.example.rabbitmq.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageReceiver {
    public void receiveMessage(String message){
        System.out.println("Received:"+message);
    }
    private CountDownLatch latch = new CountDownLatch(1);
    public CountDownLatch getLatch() {
        return latch;
    }

}
