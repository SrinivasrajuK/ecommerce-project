package com.ecommerce.inventory_service.services;

import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.common_kafka_models.dto.StockResponse;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    @Override
    @Transactional
    public StockResponse reserveStock(Long productId, Integer qty) {

        Optional<Inventory> inventoryOpt = repository.findByProductId(productId);

        if (inventoryOpt.isEmpty()) {
            return new StockResponse(false, "Inventory not found for productId: " + productId);
        }

        Inventory inventory = inventoryOpt.get();

        if (inventory.getQuantity() < qty) {
            return new StockResponse(false, "OUT_OF_STOCK");
        }

        inventory.setQuantity(inventory.getQuantity() - qty);
        repository.save(inventory);

        return new StockResponse(true, "Stock reserved successfully");
    }

    // ✅ ADD THIS METHOD (FIX)
    @Override
    @Transactional
    public StockResponse addStockBack(Long productId, Integer qty) {

        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        inv.setQuantity(inv.getQuantity() + qty);
        repository.save(inv);
        return new StockResponse(true, "Stock restored successfully");

    }
}