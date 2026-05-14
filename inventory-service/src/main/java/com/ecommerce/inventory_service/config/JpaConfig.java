package com.ecommerce.inventory_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.ecommerce.inventory.repository")
@EnableTransactionManagement
public class JpaConfig {
}
