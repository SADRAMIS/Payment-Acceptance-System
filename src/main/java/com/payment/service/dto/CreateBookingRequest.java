package com.payment.service.dto;

import java.math.BigDecimal;

public record CreateBookingRequest(BigDecimal amount, String currency) {}
