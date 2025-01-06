package com.incetutku.accountservice.service;

import com.incetutku.accountservice.dto.CustomerDto;

public interface IAccountService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountByMobileNumber(String mobileNumber);
}
