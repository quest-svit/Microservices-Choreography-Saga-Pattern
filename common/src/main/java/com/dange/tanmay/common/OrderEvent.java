package com.dange.tanmay.common;

import lombok.*;

import java.io.Serializable;

@lombok.Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent implements Event,Serializable {

    private static final long serialVersionUID = -6470090944414208496L;
    private Long orderId;
    private Long accountId;
    private Long productId;
    private Long quantity;
    private Long amount;
    private OrderStatus status;
    private InventoryStatus inventoryStatus;
    private AccountingStatus accountingStatus;

}
