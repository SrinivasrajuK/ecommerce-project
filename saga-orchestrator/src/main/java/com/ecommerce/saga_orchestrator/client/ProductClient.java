package com.ecommerce.saga_orchestrator.client;

import com.ecommerce.common_kafka_models.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8083")
public interface ProductClient {

//    @GetMapping("/products/{id}")
    @GetMapping("/products/internal/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

}
