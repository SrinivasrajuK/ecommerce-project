package com.ecommerce.common_kafka_models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockSuccessEvent {
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
