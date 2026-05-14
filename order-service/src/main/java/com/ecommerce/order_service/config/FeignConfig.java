package com.ecommerce.order_service.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {

                RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

                if (attributes == null) return;

                HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

                String authHeader = request.getHeader("Authorization");
                System.out.println("AUTH HEADER: " + request.getHeader("Authorization"));

                if (authHeader != null) {
                    template.header("Authorization", authHeader);
                }
            }
        };
    }
}
