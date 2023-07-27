package com.dange.tanmay.entity;

import com.dange.tanmay.common.AccountingStatus;
import com.dange.tanmay.common.InventoryStatus;
import com.dange.tanmay.common.OrderStatus;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@lombok.Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDER_DETAILS")
public class OrderEntity implements Serializable {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private static final long serialVersionUID = -6470090944414208496L;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    @Column(name = "account_status")
    private AccountingStatus accountingStatus;

    @Column(name = "status")
    private OrderStatus status;

}
