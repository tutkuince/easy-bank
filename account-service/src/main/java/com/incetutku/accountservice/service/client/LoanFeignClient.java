package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.CardDto;
import com.incetutku.accountservice.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans-service", fallback = LoanFallback.class)
public interface LoanFeignClient {

    @GetMapping(value = "/api/v1/loans", consumes = "application/json")
    ResponseEntity<LoanDto> fetchLoanDetailsByMobileNumber(@RequestHeader("easybank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
