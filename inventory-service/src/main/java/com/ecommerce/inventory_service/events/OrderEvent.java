package com.ecommerce.inventory_service.events;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private Long orderId;
    private Long productId;
    private Integer qty;
}
