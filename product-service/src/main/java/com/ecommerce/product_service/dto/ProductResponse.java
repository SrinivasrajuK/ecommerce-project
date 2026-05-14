package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Product;
import java.io.Serializable;


import lombok.Data;

@Data

public class ProductResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;  // 🔥 MUST MATCH product-service
}
