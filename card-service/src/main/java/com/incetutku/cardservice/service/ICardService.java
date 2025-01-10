package com.incetutku.cardservice.service;

import com.incetutku.cardservice.dto.CardDto;

public interface ICardService {
    void createLoan(String mobileNumber);

    CardDto fetchLoanByMobileNumber(String mobileNumber);

    boolean updateLoan(CardDto cardDto);

    boolean deleteLoanByMobileNumber(String mobileNumber);
}
