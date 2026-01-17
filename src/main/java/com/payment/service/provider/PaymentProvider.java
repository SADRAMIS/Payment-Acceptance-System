package com.payment.service.provider;

import com.payment.service.dto.PayRequest;
import com.payment.service.model.Payment;
import com.payment.service.model.PaymentStatus;

public interface PaymentProvider {
    PaymentStatus pay(Payment payment, PayRequest request);
    PaymentStatus checkStatus(Payment payment);
    String getName();
}
