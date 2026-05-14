package com.ecommerce.inventory_service.dto;

import lombok.Data;

@Data
public class InventoryResponse {
    private Long productId;
    private Integer quantity;
    private String status;
}
