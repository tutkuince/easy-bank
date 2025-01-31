package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.CardDto;
import com.incetutku.accountservice.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loan-service")
public interface LoanFeignClient {

    @GetMapping(value = "/api/v1/loans", consumes = "application/json")
    ResponseEntity<LoanDto> fetchLoanDetailsByMobileNumber(@RequestParam String mobileNumber);
}
