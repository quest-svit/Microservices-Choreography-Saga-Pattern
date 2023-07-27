package com.dange.tanmay.service;

import com.dange.tanmay.common.*;
import com.dange.tanmay.entity.ProductEntity;
import com.dange.tanmay.entity.ProductInventory;
import com.dange.tanmay.entity.Transaction;
import com.dange.tanmay.repository.ProductEntityRepository;
import com.dange.tanmay.repository.ProductInventoryRepository;
import com.dange.tanmay.repository.TransactionRepository;
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
    private InventoryService inventoryService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload OrderEvent order) {
        log.info("Message Received"+ order);

        if (order.getStatus() == OrderStatus.CREATED && order.getInventoryStatus() == null && order.getAccountingStatus() == null) {
            inventoryService.processCreatedMessage(order);
        } else if (order.getStatus() == OrderStatus.CREATED  && order.getInventoryStatus() == InventoryStatus.SUCCESSFUL && order.getAccountingStatus() == AccountingStatus.PAYMENT_FAILED){
            inventoryService.processRollback(order);
        } else{
            log.warn("Unknown Order Status");
        }
        log.info("Processing Complete");
    }
}
