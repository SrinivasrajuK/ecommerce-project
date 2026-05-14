package com.ecommerce.order_service.services;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.dto.*;
//import com.ecommerce.common.events.OrderEvent;
import com.ecommerce.common_kafka_models.dto.OrderEvent;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.kafka.OrderProducer;
import com.ecommerce.order_service.repository.OrderRepository;
import com.ecommerce.order_service.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderProducer producer;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductClient productClient,
                            OrderProducer producer) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.producer = producer;
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {

        System.out.println("the productId :"+ request.getProductId());
        ProductResponse product = productClient.getProduct(request.getProductId());

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Out of stock");
        }

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setUserId("raju");
        order.setTotalPrice(product.getPrice() * request.getQuantity());
        order.setStatus("CREATED");

        Order saved = orderRepository.save(order);

        // 🔥 CREATE EVENT
        OrderEvent event = new OrderEvent();
        event.setOrderId(saved.getId());
        event.setProductId(saved.getProductId());
        event.setQuantity(saved.getQuantity());
        event.setAmount(saved.getTotalPrice());

        // 🚀 SEND TO KAFKA (THIS WAS MISSING)
        producer.sendOrderEvent(event);

        OrderResponse response = new OrderResponse();
        response.setOrderId(saved.getId());
        response.setProductId(saved.getProductId());
        response.setQuantity(saved.getQuantity());
        response.setTotalPrice(saved.getTotalPrice());
        response.setStatus(saved.getStatus());

        return response;
    }
}
