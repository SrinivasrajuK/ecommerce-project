package com.ecommerce.order_service.kafka;

import com.ecommerce.common_kafka_models.dto.PaymentFailedEvent;
import com.ecommerce.common_kafka_models.dto.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderRepository repository;

    @KafkaListener(
            topics = "payment-success-v1",
            groupId = "order-group-v2",
            properties = {
                    "spring.json.use.type.headers=false",
                    "spring.json.value.default.type=com.ecommerce.common_kafka_models.dto.PaymentSuccessEvent"
            }
    )
    public void handlePaymentSuccess(PaymentSuccessEvent event) {

        log.info("📥 Payment SUCCESS: {}", event);

        Order order = repository.findById(event.getOrderId())
                .orElseThrow();

        order.setStatus("COMPLETED");

        repository.save(order);

        log.info("✅ Order COMPLETED");
    }

    @KafkaListener(
            topics = "payment-failed-v1",
            groupId = "order-group-v2",
            properties = {
                    "spring.json.use.type.headers=false",
                    "spring.json.value.default.type=com.ecommerce.common_kafka_models.dto.PaymentFailedEvent"
            }
    )
    public void handlePaymentFailure(PaymentFailedEvent event) {

        log.info("📥 Payment FAILED: {}", event);

        Order order = repository.findById(event.getOrderId())
                .orElseThrow();

        order.setStatus("FAILED");

        repository.save(order);

        log.info("❌ Order FAILED");
    }
}
