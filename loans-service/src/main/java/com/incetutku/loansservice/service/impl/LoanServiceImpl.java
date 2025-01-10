package com.incetutku.loansservice.service.impl;

import com.incetutku.loansservice.constants.LoanConstants;
import com.incetutku.loansservice.dto.LoanDto;
import com.incetutku.loansservice.entity.Loan;
import com.incetutku.loansservice.exception.LoanAlreadyExistsException;
import com.incetutku.loansservice.exception.ResourceNotFoundException;
import com.incetutku.loansservice.mappler.LoanMapper;
import com.incetutku.loansservice.repository.LoanRepository;
import com.incetutku.loansservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobile number " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoanDto fetchLoanByMobileNumber(String mobileNumber) {
        Loan savedLoan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
        return LoanMapper.mapToLoanDto(savedLoan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan selectedLoan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan Number", loanDto.getLoanNumber())
        );
        LoanMapper.mapToLoan(loanDto, selectedLoan);
        loanRepository.save(selectedLoan);
        return true;
    }

    @Override
    public boolean deleteLoanByMobileNumber(String mobileNumber) {
        Loan selectedLoan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
        loanRepository.deleteById(selectedLoan.getLoanId());
        return true;
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
