package com.payment.service.service;

import com.payment.service.dto.PayRequest;
import com.payment.service.model.Booking;
import com.payment.service.model.BookingStatus;
import com.payment.service.model.Payment;
import com.payment.service.model.PaymentStatus;
import com.payment.service.provider.PaymentProvider;
import com.payment.service.provider.PaymentProviderResolver;
import com.payment.service.repository.BookingRepository;
import com.payment.service.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProviderResolver resolver;

    public PaymentService(BookingRepository bookingRepository, PaymentRepository paymentRepository, PaymentProviderResolver resolver) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.resolver = resolver;
    }

    @Transactional
    public Payment pay(Long userId, PayRequest request) {
        Booking booking = bookingRepository.findByToken(request.token())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }
        if (booking.getStatus() != BookingStatus.NEW) {
            throw new RuntimeException("Booking not NEW");
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(new BigDecimal("100.00")); // временно
        payment.setCurrency("RUB");
        PaymentProvider provider = resolver.byCountry(request.countryCode());
        payment.setProvider(provider.getName());
        payment.setStatus(PaymentStatus.PENDING);

        payment = paymentRepository.save(payment);

        PaymentStatus newStatus = provider.pay(payment,request);
        payment.setStatus(newStatus);
        if (newStatus == PaymentStatus.SUCCESS) {
            booking.setStatus(BookingStatus.PAID);
        } else if (newStatus == PaymentStatus.FAILED) {
            booking.setStatus(BookingStatus.NEW);
        }
        return payment;
    }
}
