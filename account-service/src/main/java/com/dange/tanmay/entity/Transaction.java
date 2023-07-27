package com.dange.tanmay.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Builder
@Table(name = "TRANSACTION_DETAILS")
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name= "transaction_order_id")
    private Long orderId;

    @Column(name= "transaction_account_id")
    private Long accountId;

    @Column(name = "transaction_amount")
    Long amount;

    @CreationTimestamp
    @Column(name = "transaction_datetime")
    Instant transactionDateTime;
}
