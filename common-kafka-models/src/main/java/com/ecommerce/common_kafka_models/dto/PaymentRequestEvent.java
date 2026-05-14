package com.ecommerce.common_kafka_models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestEvent {
    private Long orderId;
    private Double amount;
}
