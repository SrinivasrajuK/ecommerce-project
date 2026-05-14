package com.ecommerce.inventory_service.consumer;

import com.ecommerce.common_kafka_models.dto.*;
import com.ecommerce.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(
            topics = "order-events-v3",
            groupId = "inventory-group-v2"
    )
    public void consumeOrderEvent(OrderEvent event) {

        System.out.println("📥 Order Received: " + event);

        StockResponse response =
                inventoryService.reserveStock(
                        event.getProductId(),
                        event.getQuantity()
                );

        if (!response.isAvailable()) {

            kafkaTemplate.send(
                    "stock-failed-v2",
                    new StockFailedEvent(
                            event.getOrderId(),
                            response.getMessage()
                    )
            );

            return;
        }

        kafkaTemplate.send(
                "stock-success-v2",
                new StockSuccessEvent(
                        event.getOrderId(),
                        event.getProductId(),
                        event.getQuantity()
                )
        );

        System.out.println("✅ Stock Reserved");
    }
}