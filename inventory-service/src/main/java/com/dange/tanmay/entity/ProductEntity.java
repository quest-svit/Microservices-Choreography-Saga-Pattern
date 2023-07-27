package com.dange.tanmay.entity;

import com.dange.tanmay.common.AccountingStatus;
import com.dange.tanmay.common.InventoryStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table(name="PAYMENT_STATUS")
@ToString
@Data
@Entity
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "inventory_status")
    private InventoryStatus status;

}
