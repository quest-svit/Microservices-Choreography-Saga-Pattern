package com.dange.tanmay.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="INVENTORY")
public class ProductInventory {
    @Id
    @Column(name = "inventory_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "product_price")
    Long productPrice;

}
