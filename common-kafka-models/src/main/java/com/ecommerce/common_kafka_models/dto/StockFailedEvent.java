package com.ecommerce.common_kafka_models.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockFailedEvent {
    private Long orderId;
    private String reason;
}
