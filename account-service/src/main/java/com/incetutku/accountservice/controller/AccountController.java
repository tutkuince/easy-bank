package com.incetutku.accountservice.controller;

import com.incetutku.accountservice.constants.AccountConstants;
import com.incetutku.accountservice.dto.CustomerDto;
import com.incetutku.accountservice.dto.ResponseDto;
import com.incetutku.accountservice.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Account in EasyBank",
        description = "CRUD REST APIs in EasyBank to CREATE, UPDATE, FETCH and DELETE Account Details"
)
@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer and Account inside EasyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer and Account Details based on account number"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer and Account details based on mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccountDetailsByMobileNumber(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
            String mobileNumber
    ) {
        return ResponseEntity.ok(accountService.fetchAccountByMobileNumber(mobileNumber));
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer and Account Details based on mobile number"
    )
    @ApiResponses( {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteAccountByMobileNumber(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
            String mobileNumber
    ) {
        boolean isDeleted = accountService.deleteAccountByMobileNumber(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
    }
}
