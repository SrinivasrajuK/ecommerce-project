package com.ecommerce.notification_service.producer;

import com.ecommerce.notification_service.dto.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(NotificationEvent event) {

        kafkaTemplate.send(
                "notification-topic",
                event
        );
    }
}
