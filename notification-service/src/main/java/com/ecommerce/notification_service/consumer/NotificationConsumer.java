package com.ecommerce.notification_service.consumer;

import com.ecommerce.common_kafka_models.dto.NotificationEvent;
import com.ecommerce.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(
            topics = "notification-topic",
            groupId = "notification-group"
    )
    public void consume(NotificationEvent event) {

        System.out.println("📥 Notification Event Received");

        emailService.sendEmail(
                event.getEmail(),
                event.getSubject(),
                event.getMessage()
        );
    }
}
