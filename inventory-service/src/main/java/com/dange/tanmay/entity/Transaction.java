package com.dange.tanmay.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.Instant;

@lombok.Builder
@Table(name = "TRANSACTION_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name= "transaction_order_id")
    private Long orderId;

    @Column(name= "transaction_product_id")
    private Long productId;

    @Column(name = "transaction_quantity")
    private Long quantity;

    @CreationTimestamp
    @Column(name = "transaction_datetime")
    Instant transactionDateTime;
}
