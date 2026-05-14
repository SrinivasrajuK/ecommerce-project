package com.ecommerce.inventory_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderEvents() {
        return new NewTopic("order-events-v3", 1, (short) 1);
    }

    @Bean
    public NewTopic stockSuccess() {
        return new NewTopic("stock-success", 1, (short) 1);
    }

    @Bean
    public NewTopic stockFailed() {
        return new NewTopic("stock-failed", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentRequest() {
        return new NewTopic("payment-request", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentSuccess() {
        return new NewTopic("payment-success", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentFailed() {
        return new NewTopic("payment-failed", 1, (short) 1);
    }

    @Bean
    public NewTopic orderCancel() {
        return new NewTopic("order-cancel", 1, (short) 1);
    }

    @Bean
    public NewTopic restoreStock() {
        return new NewTopic("restore-stock", 1, (short) 1);
    }
    @Bean
    public NewTopic notificationTopic() {
        return new NewTopic(
                "notification-topic",
                1,
                (short) 1
        );
    }
}
