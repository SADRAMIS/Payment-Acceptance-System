package com.payment.service.dto;

import java.util.UUID;

public record BookingTokenResponse(Long bookingId, UUID token) {}
