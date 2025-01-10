package com.incetutku.loansservice.service;

import com.incetutku.loansservice.dto.LoanDto;

public interface ILoanService {
    void createLoan(String mobileNumber);

    LoanDto fetchLoanByMobileNumber(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoanByMobileNumber(String mobileNumber);
}
