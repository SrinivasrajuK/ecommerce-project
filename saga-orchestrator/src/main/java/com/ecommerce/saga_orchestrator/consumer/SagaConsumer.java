package com.ecommerce.saga_orchestrator.consumer;

import com.ecommerce.common_kafka_models.dto.*;
import com.ecommerce.saga_orchestrator.client.ProductClient;
import com.ecommerce.saga_orchestrator.producer.SagaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SagaConsumer {

    private final SagaProducer producer;
    private final ProductClient productClient;

    @KafkaListener(
            topics = "stock-success-v2",
            groupId = "saga-group-v2"
    )
    public void handleStockSuccess(StockSuccessEvent event) {

        log.info("📥 Stock success received: {}", event);

        ProductResponse product =
                productClient.getProduct(event.getProductId());

        double amount =
                product.getPrice() * event.getQuantity();

        PaymentRequestEvent paymentEvent =
                new PaymentRequestEvent(
                        event.getOrderId(),
                        amount
                );

        producer.sendPaymentRequest(paymentEvent);
    }

    @KafkaListener(
            topics = "stock-failed-v2",
            groupId = "saga-group-v2"
    )
    public void handleStockFailed(StockFailedEvent event) {

        log.info("❌ Stock failed: {}", event);

        producer.sendOrderCancel(
                new OrderCancelEvent(
                        event.getOrderId(),
                        event.getReason()
                )
        );
    }
}