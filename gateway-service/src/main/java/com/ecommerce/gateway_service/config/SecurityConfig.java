//package com.ecommerce.gateway_service.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.*;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/auth/**").permitAll()   // ✅ THIS LINE IS CRITICAL
//                        .anyExchange().authenticated()
//                ).build();
//    }
//}
