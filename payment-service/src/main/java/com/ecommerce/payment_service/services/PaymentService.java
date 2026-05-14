package com.ecommerce.payment_service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class PaymentService {

    public boolean processPayment(Double amount) {

        log.info("💳 Processing payment: {}", amount);

        // 🔥 Simulate real payment
        boolean success = new Random().nextBoolean();

        if (success) {
            log.info("✅ Payment SUCCESS");
        } else {
            log.info("❌ Payment FAILED");
        }

        return success;
    }
}
