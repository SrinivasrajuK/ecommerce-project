package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.response.ApiResponse;
import com.ecommerce.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest req) {
        System.out.println("in create");
        return ApiResponse.success("Product created", service.create(req));
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll() {
        System.out.println("in get all");
        return ApiResponse.success("All products fetched", service.getAll());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest req) {
        System.out.println("in update");
        return ApiResponse.success("Product updated", service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        System.out.println("in delete");
        service.delete(id);
        return ApiResponse.success("Product deleted", "SUCCESS");
    }
    @GetMapping("/internal/{id}")
    public ProductResponse getInternalProduct(@PathVariable Long id) {
        return service.getById(id);
    }
}