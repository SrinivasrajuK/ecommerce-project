package com.ecommerce.order_service.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private String status;
}
