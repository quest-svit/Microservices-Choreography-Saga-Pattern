package com.dange.tanmay.service;

import com.dange.tanmay.common.*;
import com.dange.tanmay.entity.OrderEntity;
import com.dange.tanmay.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class QueueConsumer {

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = {"${response.queue.name}"})
    public void receive(@Payload OrderEvent event) {


        if(event.getStatus() == OrderStatus.CREATED && event.getInventoryStatus()==InventoryStatus.SUCCESSFUL && event.getAccountingStatus()== AccountingStatus.PAYMENT_SUCCESSFUL ){
            orderService.process(event, OrderStatus.COMPLETED);
        } else{
            orderService.process(event, OrderStatus.CANCELLED);
        }
    }
}
