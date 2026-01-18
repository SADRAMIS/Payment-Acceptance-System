package com.payment.service.provider;

import com.payment.service.dto.PayRequest;
import com.payment.service.model.Payment;
import com.payment.service.model.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class RuPaymentProvider implements PaymentProvider{

    @Override
    public PaymentStatus pay(Payment payment, PayRequest request) {
        return PaymentStatus.SUCCESS;
    }

    @Override
    public PaymentStatus checkStatus(Payment payment) {
        return payment.getStatus();
    }

    @Override
    public String getName() {
        return "RU";
    }
}
