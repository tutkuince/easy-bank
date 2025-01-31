package com.incetutku.accountservice.service.impl;

import com.incetutku.accountservice.dto.AccountDto;
import com.incetutku.accountservice.dto.CardDto;
import com.incetutku.accountservice.dto.CustomerDetailsDto;
import com.incetutku.accountservice.dto.LoanDto;
import com.incetutku.accountservice.entity.Account;
import com.incetutku.accountservice.entity.Customer;
import com.incetutku.accountservice.exception.ResourceNotFoundException;
import com.incetutku.accountservice.mapper.AccountMapper;
import com.incetutku.accountservice.mapper.CustomerMapper;
import com.incetutku.accountservice.repository.AccountRepository;
import com.incetutku.accountservice.repository.CustomerRepository;
import com.incetutku.accountservice.service.client.CardFeignClient;
import com.incetutku.accountservice.service.client.ICustomerService;
import com.incetutku.accountservice.service.client.LoanFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardFeignClient cardFeignClient;
    private final LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDto getCustomerDetailsByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        ResponseEntity<CardDto> cardDtoResponseEntity = cardFeignClient.fetchCardDetailsByMobileNumber(mobileNumber);
        customerDetailsDto.setCardDto(cardDtoResponseEntity.getBody());

        ResponseEntity<LoanDto> loanDtoResponseEntity = loanFeignClient.fetchLoanDetailsByMobileNumber(mobileNumber);
        customerDetailsDto.setLoanDto(loanDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
