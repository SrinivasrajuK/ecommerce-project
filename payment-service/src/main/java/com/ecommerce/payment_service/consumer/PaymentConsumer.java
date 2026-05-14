package com.ecommerce.payment_service.consumer;

import com.ecommerce.common_kafka_models.dto.NotificationEvent;
import com.ecommerce.common_kafka_models.dto.PaymentFailedEvent;
import com.ecommerce.common_kafka_models.dto.PaymentRequestEvent;
import com.ecommerce.common_kafka_models.dto.PaymentSuccessEvent;
import com.ecommerce.payment_service.producer.PaymentProducer;
import com.ecommerce.payment_service.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final PaymentService paymentService;
    private final PaymentProducer producer;

    @KafkaListener(
            topics = "payment-request-v1",
            groupId = "payment-group-v2"
    )
    public void consume(PaymentRequestEvent event) {

        log.info("📥 Payment Request: {}", event);

        boolean success =
                paymentService.processPayment(event.getAmount());

        if (success) {

            // ✅ PAYMENT SUCCESS EVENT
            producer.sendSuccess(
                    new PaymentSuccessEvent(event.getOrderId())
            );

            // ✅ SEND NOTIFICATION EVENT
            producer.sendNotification(

                    new NotificationEvent(

                            "reddyjyothi1101@gmail.com",

                            "Payment Successful",

                            "Your payment was successful for Order ID: "
                                    + event.getOrderId()
                                    + "\nAmount: ₹"
                                    + event.getAmount()
                    )
            );

        } else {

            producer.sendFailure(

                    new PaymentFailedEvent(
                            event.getOrderId(),
                            "PAYMENT_FAILED"
                    )
            );

            // ✅ SEND NOTIFICATION EVENT
            producer.sendNotification(

                    new NotificationEvent(

                            "reddyjyothi1101@gmail.com",

                            "Payment Failed",

                            "Your payment was Failed for Order ID: "
                                    + event.getOrderId()
                                    + "\nAmount: ₹"
                                    + event.getAmount()
                    )
            );
        }
    }
}