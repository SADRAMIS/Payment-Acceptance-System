package com.payment.service.dto;

import com.payment.service.model.BookingStatus;

import java.util.UUID;

public record BookingDto(Long id, UUID token, BookingStatus status) {}
