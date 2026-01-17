package com.payment.service.dto;

import com.payment.service.model.PaymentStatus;

public record PaymentDto (Long id, PaymentStatus status, String provider){}
