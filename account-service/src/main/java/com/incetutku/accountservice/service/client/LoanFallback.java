package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanFallback implements LoanFeignClient {

    @Override
    public ResponseEntity<LoanDto> fetchLoanDetailsByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
