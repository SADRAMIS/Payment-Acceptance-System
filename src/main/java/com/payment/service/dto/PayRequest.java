package com.payment.service.dto;

import java.util.UUID;

public record PayRequest(UUID token, String cardNumber, String cardHolder, String countryCode) {}
