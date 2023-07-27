package com.dange.tanmay.entity;

import com.dange.tanmay.common.AccountingStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="PAYMENT_STATUS")
@ToString
@Data
@Entity
public class AccountEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "payment_status")
    private AccountingStatus status;

}
