package com.payment.service.repository;

import com.payment.service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByToken(UUID token);
    List<Booking> findByUserId(Long userId);
}
