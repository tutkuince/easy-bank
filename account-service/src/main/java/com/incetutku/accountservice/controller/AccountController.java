package com.incetutku.accountservice.controller;

import com.incetutku.accountservice.constants.AccountConstants;
import com.incetutku.accountservice.dto.CustomerDto;
import com.incetutku.accountservice.dto.ResponseDto;
import com.incetutku.accountservice.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
    }

    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccountDetailsByMobileNumber(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
            String mobileNumber
    ) {
        return ResponseEntity.ok(accountService.fetchAccountByMobileNumber(mobileNumber));
    }

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
