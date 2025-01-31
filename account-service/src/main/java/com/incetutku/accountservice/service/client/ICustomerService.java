package com.incetutku.accountservice.service.client;

import com.incetutku.accountservice.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto getCustomerDetailsByMobileNumber(String mobileNumber);
}
