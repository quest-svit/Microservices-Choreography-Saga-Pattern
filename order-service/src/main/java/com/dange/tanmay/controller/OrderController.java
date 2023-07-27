package com.dange.tanmay.controller;

import com.dange.tanmay.common.OrderEvent;
import com.dange.tanmay.common.OrderStatus;
import com.dange.tanmay.service.QueueSender;
import com.dange.tanmay.entity.OrderEntity;
import com.dange.tanmay.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    OrderRepository repository;

    @Autowired
    QueueSender sender;

    @RequestMapping("/createOrder")
    OrderEntity createOrder(@RequestParam(value = "accountId") String accountId,
        @RequestParam(value = "productId") String productId,
        @RequestParam(value="quantity") String quantity){
        System.out.println("Order Received");
            log.info("\nReceived Create Order :" +
            "\nProduct :" + productId +
            "\nQuantity :" + quantity +
            "\naccountName : " + accountId);
            
            log.info("Fetching Price of Product");
           /* String url="http://localhost:8085/getPrice?product="+product;

            String response = fetch(url);
            */
            String response = "100"; //Hardcoded product price
            log.info("Price of "+productId +": "+ response);
            Integer price =  Integer.parseInt(response);
            Integer amount = Integer.parseInt(quantity) * price;

            OrderEntity order = OrderEntity.builder().accountId(Long.valueOf(accountId))
                .productId(Long.valueOf(productId))
                .quantity(Long.valueOf(quantity))
                .status(OrderStatus.CREATED)
                .amount(Long.valueOf(amount)).build();


            order = repository.save(order);

            OrderEvent event =  OrderEvent.builder().orderId(order.getOrderId())
                    .accountId(order.getAccountId())
                    .productId(order.getProductId())
                    .quantity(order.getQuantity())
                    .amount(order.getAmount())
                    .status(order.getStatus())
                    .build();


            sender.send(event);

        return order;
    }



    @RequestMapping("/getAll")
    List<OrderEntity> getAllOrders(){
        return repository.findAll();
    }

    @RequestMapping("/get")
    OrderEntity getProducts(@RequestParam Long orderId){
        return repository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not found for the given ID"));
    }

/*    private String fetch(String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        return response;
    }*/

}
