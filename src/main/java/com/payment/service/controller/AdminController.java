package com.payment.service.controller;

import com.payment.service.dto.BookingDto;
import com.payment.service.dto.PaymentDto;
import com.payment.service.repository.BookingRepository;
import com.payment.service.repository.PaymentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public AdminController(BookingRepository bookingRepository,
                           PaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/bookings")
    public List<BookingDto> allBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> new BookingDto(b.getId(), b.getToken(), b.getStatus()))
                .toList();
    }
    @GetMapping("/payments")
    public List<PaymentDto> allPayments() {
        return paymentRepository.findAll().stream()
                .map(p -> new PaymentDto(p.getId(), p.getStatus(), p.getProvider()))
                .toList();
    }
}
