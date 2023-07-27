package com.dange.tanmay.service;

import com.dange.tanmay.common.AccountingStatus;
import com.dange.tanmay.common.OrderEvent;
import com.dange.tanmay.entity.AccountBalance;
import com.dange.tanmay.entity.AccountEntity;
import com.dange.tanmay.entity.Transaction;
import com.dange.tanmay.repository.AccountEntityRepository;
import com.dange.tanmay.repository.AccountRepository;
import com.dange.tanmay.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @Autowired
    private QueueSender sender;


    public void processCreatedMessage(OrderEvent order){
        Transaction tr = getTransaction(order, order.getAmount());

        /*AccountEvent responseEvent = new AccountEvent();
        responseEvent.setOrderId(order.getOrderId());*/

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setOrderId(order.getOrderId());

        AccountBalance accountBalance = accountRepository.findById(tr.getAccountId()).orElseThrow(() -> new RuntimeException("Account Not Found"));
        // check if the orderAmount <= Account Balance
        if (order.getAmount() <= accountBalance.getBalance()) {
            accountBalance.setBalance(accountBalance.getBalance() - order.getAmount());

            transactionRepository.save(tr);
            accountRepository.save(accountBalance);
            extracted(accountEntity, AccountingStatus.PAYMENT_SUCCESSFUL, order);
            log.info("Payment Successful");

            //Publish the event again
            sender.sendSuccess(order);
        } else {
            log.info("Not enough money in account. Payment Failed");
            extracted(accountEntity, AccountingStatus.PAYMENT_FAILED, order);

            //Publish the event again
            sender.sendFailure(order);
        }

        accountEntityRepository.save(accountEntity);

        log.info("Processing Complete");

    }

    private void extracted(AccountEntity accountEntity, AccountingStatus accountingStatus, OrderEvent responseEvent) {
        accountEntity.setStatus(accountingStatus);
        responseEvent.setAccountingStatus(accountingStatus);
    }

    private Transaction getTransaction(OrderEvent order, Long amount) {
        return  Transaction.builder().accountId(order.getAccountId())
                .orderId(order.getOrderId())
                .amount(amount).build();
    }

    public void processRollback(OrderEvent order){
        log.info("Cancellation Request received for order="+order.getOrderId());
        AccountEntity accountEntity = accountEntityRepository.findById(order.getAccountId()).orElseThrow(() -> new RuntimeException("Account Entity Not Found"));
        if (accountEntity.getStatus() == AccountingStatus.PAYMENT_SUCCESSFUL) {
            Transaction tr = getTransaction(order, -order.getAmount());

            //get the accountBalance object from repository using accountID
            AccountBalance accountBalance = accountRepository.findById(tr.getAccountId()).orElseThrow(() -> new RuntimeException("Account ID not Found"));
            accountBalance.setBalance(accountBalance.getBalance() + order.getAmount());

            transactionRepository.save(tr);
            accountRepository.save(accountBalance);

            log.info("Payment Rollback Successful");

            accountEntity.setStatus(AccountingStatus.PAYMENT_ROLLEDBACK);
            accountEntityRepository.save(accountEntity);
            log.info("Processing Complete");
        }

    }


}
