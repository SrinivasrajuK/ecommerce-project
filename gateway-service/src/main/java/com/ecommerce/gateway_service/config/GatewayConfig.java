package com.ecommerce.gateway_service.config;

import com.ecommerce.gateway_service.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.*;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public GatewayConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("user-service", r -> r
                        .path("/auth/**")
                        .uri("http://localhost:8081")
                )

                .route("order-service", r -> r
                        .path("/orders/**")
                        .uri("http://localhost:8084")
                )

                .route("product-service", r -> r
                        .path("/products/**")
                        .uri("http://localhost:8083")
                )

                .build();
    }
}