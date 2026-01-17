package com.payment.service.repository;

import com.payment.service.model.Booking;
import com.payment.service.model.Payment;
import com.payment.service.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByBooking(Booking booking);
}
