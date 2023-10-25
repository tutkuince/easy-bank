package io.ince.easybank.accounts.service;

import io.ince.easybank.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - mobile phone
     * @return CustomerDto
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto - CustomerDto Object
     * @return - boolean
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean - indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
