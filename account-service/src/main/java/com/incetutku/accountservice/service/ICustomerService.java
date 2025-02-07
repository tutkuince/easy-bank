package com.incetutku.accountservice.service;

import com.incetutku.accountservice.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto getCustomerDetailsByMobileNumber(String mobileNumber, String correlationId);
}
