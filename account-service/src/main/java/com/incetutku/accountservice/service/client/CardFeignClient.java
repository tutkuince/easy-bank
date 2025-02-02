package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("card-service")
public interface CardFeignClient {

    @GetMapping(value = "/api/v1/cards", consumes = "application/json")
    ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(@RequestParam String mobileNumber);
}
