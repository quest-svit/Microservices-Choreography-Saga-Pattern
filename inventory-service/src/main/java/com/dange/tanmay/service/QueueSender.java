package com.dange.tanmay.service;


import com.dange.tanmay.common.OrderEvent;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QueueSender {

        @Autowired
        private RabbitTemplate rabbitTemplate;

        @Autowired
        private Queue successQueue;

        @Autowired
        private Queue failureQueue;

        public void sendSuccess(OrderEvent responseEvent) {
                System.out.println("Sending response");
                rabbitTemplate.convertAndSend(this.successQueue.getName(), responseEvent);
        }

        public void sendFailure(OrderEvent responseEvent) {
                System.out.println("Sending response");
                rabbitTemplate.convertAndSend(this.failureQueue.getName(), responseEvent);
        }

}