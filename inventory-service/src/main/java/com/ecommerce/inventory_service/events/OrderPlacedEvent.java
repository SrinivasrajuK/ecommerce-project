package com.ecommerce.inventory_service.events;

import lombok.Data;

@Data
public class OrderPlacedEvent {
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
