package com.payment.service.service;

import com.payment.service.dto.CreateBookingRequest;
import com.payment.service.model.Booking;
import com.payment.service.model.BookingStatus;
import com.payment.service.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(Long userId, CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setToken(UUID.randomUUID());
        booking.setStatus(BookingStatus.NEW);
        return bookingRepository.save(booking);
    }

    public Booking getUserBookingOrThrow(Long id, Long userId) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (!booking.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }

        return booking;
    }

    @Transactional
    public void cancelBooking(Long id, Long userId) {
        Booking booking = getUserBookingOrThrow(id, userId);
        if (booking.getStatus() == BookingStatus.PAID) {
            throw new RuntimeException("Already paid");
        }

        booking.setStatus(BookingStatus.CANCELED);
    }
}
