package com.ecommerce.order_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();
}
