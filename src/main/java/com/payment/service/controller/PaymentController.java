package com.payment.service.controller;

import com.payment.service.dto.PayRequest;
import com.payment.service.dto.PaymentDto;
import com.payment.service.model.Payment;
import com.payment.service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    private Long currentUserId() {
        return 1L;
    }

    @PostMapping
    public PaymentDto pay(@RequestBody PayRequest request) {
        Payment payment = paymentService.pay(currentUserId(), request);
        return new PaymentDto(payment.getId(), payment.getStatus(), payment.getProvider());
    }
}
