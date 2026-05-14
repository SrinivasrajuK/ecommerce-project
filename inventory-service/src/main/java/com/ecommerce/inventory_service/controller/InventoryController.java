package com.ecommerce.inventory_service.controller;

import com.ecommerce.common_kafka_models.dto.StockResponse;
import com.ecommerce.inventory_service.dto.InventoryRequest;
import com.ecommerce.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // ================= RESERVE STOCK =================
    @PostMapping("/reserve")
    public StockResponse reserveStock(@RequestBody InventoryRequest request) {

        return inventoryService.reserveStock(
                request.getProductId(),
                request.getQuantity()
        );
    }

    // ================= RESTORE STOCK =================
    @PostMapping("/restore")
    public StockResponse restoreStock(@RequestBody InventoryRequest request) {

        return inventoryService.addStockBack(
                request.getProductId(),
                request.getQuantity()
        );
    }
}