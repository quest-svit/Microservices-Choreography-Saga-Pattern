package com.dange.tanmay.controller;

import com.dange.tanmay.entity.ProductInventory;
import com.dange.tanmay.repository.ProductInventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class InventoryController {

    @Autowired
    private ProductInventoryRepository repository;

    @RequestMapping("/getAll")
    List<ProductInventory> getAllProducts(){
        return repository.findAll();
    }

    @RequestMapping("/get")
    ProductInventory getProducts(@RequestParam Long productId){
        return repository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not found for the given ID"));
    }

    @PostMapping("/add")
    ProductInventory addProducts(@RequestBody ProductInventory product){
        return repository.save(product);
    }




}
