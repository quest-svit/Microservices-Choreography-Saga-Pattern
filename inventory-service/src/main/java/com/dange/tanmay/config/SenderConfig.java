package com.dange.tanmay.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SenderConfig {

    @Value("${success.response.queue.name}")
    private String successQueueName;

    @Value("${failure.response.queue.name}")
    private String failureQueueName;


    @Bean
    public Queue successQueue() {
        return new Queue(successQueueName, true);
    }

    @Bean
    public Queue failureQueue() {
        return new Queue(failureQueueName, true);
    }

}
