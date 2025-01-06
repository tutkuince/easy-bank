package com.incetutku.accountservice.controller;

import com.incetutku.accountservice.constants.AccountConstants;
import com.incetutku.accountservice.dto.CustomerDto;
import com.incetutku.accountservice.dto.ResponseDto;
import com.incetutku.accountservice.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
}
