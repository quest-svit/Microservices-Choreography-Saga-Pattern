package com.dange.tanmay.service;

import com.dange.tanmay.common.*;
import com.dange.tanmay.entity.OrderEntity;
import com.dange.tanmay.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public void process(OrderEvent event, OrderStatus status){
        OrderEntity order =  orderRepository.findById(event.getOrderId()).orElseThrow(()->new RuntimeException("Order Not Found"));
        order.setStatus(status);
        order.setInventoryStatus(event.getInventoryStatus());
        order.setAccountingStatus(event.getAccountingStatus());
        orderRepository.save(order);
    }
}
