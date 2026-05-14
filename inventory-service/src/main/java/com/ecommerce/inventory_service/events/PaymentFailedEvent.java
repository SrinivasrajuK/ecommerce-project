package com.ecommerce.inventory_service.events;

import lombok.Data;

@Data
public class PaymentFailedEvent {
    private Long orderId;
    private String reason;
}
