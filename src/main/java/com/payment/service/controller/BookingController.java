package com.payment.service.controller;

import com.payment.service.dto.BookingDto;
import com.payment.service.dto.BookingTokenResponse;
import com.payment.service.dto.CreateBookingRequest;
import com.payment.service.model.Booking;
import com.payment.service.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    private Long currentUserId() {
        return 1L;
    }

    @PostMapping
    public BookingTokenResponse create(@RequestBody CreateBookingRequest request) {
        Booking booking = bookingService.createBooking(currentUserId(), request);
        return new BookingTokenResponse(booking.getId(),booking.getToken());
    }

    @GetMapping("/{id}")
    public BookingDto get(@PathVariable Long id) {
        Booking booking = bookingService.getUserBookingOrThrow(id, currentUserId());
        return new BookingDto(booking.getId(), booking.getToken(), booking.getStatus());
    }

    @PostMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id) {
        bookingService.cancelBooking(id, currentUserId());
    }
}
