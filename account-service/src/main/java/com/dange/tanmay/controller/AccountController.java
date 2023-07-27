package com.dange.tanmay.controller;

import com.dange.tanmay.entity.AccountBalance;
import com.dange.tanmay.repository.AccountRepository;
import lombok.extern.log4j.Log4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @RequestMapping("/getAll")
    List<AccountBalance> getAllAccounts(){
        return repository.findAll();
    }

    @RequestMapping("/get")
    AccountBalance getAccount(@RequestParam Long accountId){
        return repository.findById(accountId).orElseThrow(() -> new RuntimeException("Product Not found for the given ID"));
    }

    @PostMapping("/add")
    AccountBalance addProducts(@RequestBody AccountBalance accountBalance){
        return repository.save(accountBalance);
    }


}
