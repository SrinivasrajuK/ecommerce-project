package com.ecommerce.saga_orchestrator.producer;

import com.ecommerce.common_kafka_models.dto.OrderCancelEvent;
import com.ecommerce.common_kafka_models.dto.PaymentRequestEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SagaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentRequest(PaymentRequestEvent event) {

        System.out.println("💰 Sending Payment Request");

        kafkaTemplate.send("payment-request-v1", event);
    }

    public void sendOrderCancel(OrderCancelEvent event) {

        kafkaTemplate.send("order-cancel-v1", event);
    }
}