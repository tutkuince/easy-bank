package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card-service", fallback = CardFallback.class)
public interface CardFeignClient {

    @GetMapping(value = "/api/v1/cards", consumes = "application/json")
    ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(@RequestHeader("easybank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
