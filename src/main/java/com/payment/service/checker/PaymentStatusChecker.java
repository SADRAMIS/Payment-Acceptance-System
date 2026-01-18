package com.payment.service.checker;

import com.payment.service.model.Booking;
import com.payment.service.model.BookingStatus;
import com.payment.service.model.Payment;
import com.payment.service.model.PaymentStatus;
import com.payment.service.provider.PaymentProvider;
import com.payment.service.provider.PaymentProviderResolver;
import com.payment.service.repository.PaymentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentStatusChecker {
    private final PaymentRepository paymentRepository;
    private final PaymentProviderResolver resolver;

    public PaymentStatusChecker(PaymentRepository paymentRepository, PaymentProviderResolver resolver) {
        this.paymentRepository = paymentRepository;
        this.resolver = resolver;
    }

    @Scheduled(fixedDelay = 300_000) // 5 минут
    @Transactional
    public void checkPending() {
        List<Payment> pending = paymentRepository.findByStatus(PaymentStatus.PENDING);
        for (Payment p : pending) {
            PaymentProvider provider = resolver.byPayment(p);
            PaymentStatus newStatus = provider.checkStatus(p);
            if (newStatus != p.getStatus()) {
                p.setStatus(newStatus);
                if (newStatus == PaymentStatus.SUCCESS) {
                    Booking b = p.getBooking();
                    b.setStatus(BookingStatus.PAID);
                }
            }
        }
    }


}
