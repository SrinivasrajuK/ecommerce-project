package com.ecommerce.payment_service.producer;

import com.ecommerce.common_kafka_models.dto.NotificationEvent;
import com.ecommerce.common_kafka_models.dto.PaymentFailedEvent;
import com.ecommerce.common_kafka_models.dto.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSuccess(PaymentSuccessEvent event) {

        kafkaTemplate.send("payment-success-v1", event);
    }

    public void sendFailure(PaymentFailedEvent event) {

        kafkaTemplate.send("payment-failed-v1", event);
    }
    public void sendNotification(NotificationEvent event) {

        kafkaTemplate.send("notification-topic", event);
    }
}