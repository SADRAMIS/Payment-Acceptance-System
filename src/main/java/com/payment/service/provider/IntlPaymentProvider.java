package com.payment.service.provider;

import com.payment.service.dto.PayRequest;
import com.payment.service.model.Payment;
import com.payment.service.model.PaymentStatus;

public class IntlPaymentProvider implements PaymentProvider {
    @Override
    public PaymentStatus pay(Payment payment, PayRequest request) {
        // имитация: PENDING, чтобы было что опрашивать
        return PaymentStatus.PENDING;
    }

    @Override
    public PaymentStatus checkStatus(Payment payment) {
        // например, через время становится SUCCESS
        return PaymentStatus.SUCCESS;
    }

    @Override
    public String getName() {
        return "INTL";
    }
}
