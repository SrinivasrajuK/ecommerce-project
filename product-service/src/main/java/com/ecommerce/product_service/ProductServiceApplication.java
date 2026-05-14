package com.ecommerce.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableCaching   // 🔥 IMPORTANT
//@EnableFeignClients
public class ProductServiceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
