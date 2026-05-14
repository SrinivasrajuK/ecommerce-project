package com.ecommerce.common_kafka_models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {

    private String email;

    private String subject;

    private String message;
}
