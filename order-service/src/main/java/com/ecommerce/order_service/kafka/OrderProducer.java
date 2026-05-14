package com.ecommerce.order_service.kafka;

import com.ecommerce.common_kafka_models.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderEvent(OrderEvent event) {

        System.out.println("📤 Sending Order Event");

        kafkaTemplate.send("order-events-v3", event);
    }
}