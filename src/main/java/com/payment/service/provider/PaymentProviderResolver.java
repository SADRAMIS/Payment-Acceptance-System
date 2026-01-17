package com.payment.service.provider;

import com.payment.service.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class PaymentProviderResolver {
    private final Map<String, PaymentProvider> providersByName;

    public PaymentProviderResolver(RuPaymentProvider ru, IntlPaymentProvider intl) {
        this.providersByName = Map.of(ru.getName(), ru, intl.getName(), intl);
    }

    public PaymentProvider byCountry(String countryCode) {
        if ("RU".equalsIgnoreCase(countryCode)) {
            return providersByName.get("RU");
        }
        return providersByName.get("INTL");
    }

    public PaymentProvider byPayment(Payment payment) {
        return providersByName.get(payment.getProvider());
    }
}
