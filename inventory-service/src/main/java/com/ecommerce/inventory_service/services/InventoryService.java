package com.ecommerce.inventory_service.services;

import com.ecommerce.common_kafka_models.dto.StockResponse;
import com.ecommerce.inventory_service.dto.InventoryRequest;

public interface InventoryService {

    StockResponse reserveStock(Long productId, Integer qty);

    StockResponse addStockBack(Long productId, Integer qty);
}
