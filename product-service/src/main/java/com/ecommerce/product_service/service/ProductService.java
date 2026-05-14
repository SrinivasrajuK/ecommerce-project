package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.exception.ProductNotFoundException;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    @CachePut(value = "products", key = "#result.id")
    public ProductResponse create(ProductRequest req) {

        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());

        Product saved = repo.save(p);   // ENTITY

        return map(saved);              // DTO
    }

    public List<ProductResponse> getAll() {

        List<Product> products = repo.findAll();

        return products.stream()
                .map(this::map)
                .toList();
    }

//    public Product getById(Long id) {
//        return repo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//    }

    @Cacheable(value = "products", key = "#id")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProduct")
    public ProductResponse getById(Long id) {

        System.out.println("DB CALL → Product Service");

        Product product = repo.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );

        return map(product);
    }

    public Product getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );
    }
    private ProductResponse map(Product p) {

        ProductResponse res = new ProductResponse();

        res.setId(p.getId());
        res.setName(p.getName());
        res.setDescription(p.getDescription());
        res.setPrice(p.getPrice());
        res.setQuantity(p.getQuantity());

        return res;
    }

    public ProductResponse fallbackGetProduct(Long id, Throwable ex) {

        System.out.println("⚠ Circuit Breaker activated → fallback triggered");

        ProductResponse fallback = new ProductResponse();
        fallback.setId(id);
        fallback.setName("FALLBACK PRODUCT");
        fallback.setDescription("Service temporarily unavailable");
        fallback.setPrice(0);
        fallback.setQuantity(0);

        return fallback;
    }

    @CachePut(value = "products", key = "#id")
    public ProductResponse update(Long id, ProductRequest req) {

        Product p = getEntityById(id);

        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());

        Product saved = repo.save(p);   // ENTITY

        return map(saved);              // DTO CONVERSION
    }

    @CacheEvict(value = "products", key = "#id")
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Product getProduct(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );
    }
}