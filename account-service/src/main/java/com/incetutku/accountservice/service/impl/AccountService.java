package com.incetutku.accountservice.service.impl;

import com.incetutku.accountservice.dto.CustomerDto;
import com.incetutku.accountservice.repository.AccountRepository;
import com.incetutku.accountservice.repository.CustomerRepository;
import com.incetutku.accountservice.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
