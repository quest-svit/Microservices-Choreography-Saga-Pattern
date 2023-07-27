package com.dange.tanmay;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class InventoryServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApp.class, args);
    }
}