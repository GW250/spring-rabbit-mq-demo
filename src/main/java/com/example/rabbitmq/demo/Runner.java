package com.example.rabbitmq.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final MessageReceiver receiver;

    public Runner(MessageReceiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        for (int i = 0; i < 100000000; i++) {
            rabbitTemplate.convertAndSend(DemoApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ"+i);
            receiver.getLatch().await(1, TimeUnit.MILLISECONDS);
        }
    }

}
