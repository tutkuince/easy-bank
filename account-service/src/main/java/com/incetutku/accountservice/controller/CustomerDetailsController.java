package com.incetutku.accountservice.controller;

import com.incetutku.accountservice.dto.CustomerDetailsDto;
import com.incetutku.accountservice.dto.ErrorResponseDto;
import com.incetutku.accountservice.service.client.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "REST APIs for Customers in EasyBank",
        description = "REST APIs in EasyBank to FETCH customer details"
)
@RestController
@RequestMapping(path = "/api/v1/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CustomerDetailsController {

    private final ICustomerService customerService;

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer Details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetailsByMobileNumber(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
            String mobileNumber) {
        return ResponseEntity.ok(customerService.getCustomerDetailsByMobileNumber(mobileNumber));
    }
}
