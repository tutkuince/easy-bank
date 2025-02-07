package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.CardDto;
import com.incetutku.accountservice.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardFallback implements CardFeignClient {

    @Override
    public ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
