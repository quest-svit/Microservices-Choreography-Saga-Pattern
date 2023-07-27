package com.dange.tanmay.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ACCOUNTS")
public class AccountBalance {
    @Id
    @Column(name = "account_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "account_name")
    String name;

    @Column(name = "account_balance")
    Long balance;

}
