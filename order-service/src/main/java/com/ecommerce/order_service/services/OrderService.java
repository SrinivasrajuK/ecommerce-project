package com.ecommerce.order_service.services;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);
}
