package com.incetutku.loansservice.repository;

import com.incetutku.loansservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMobileNumber(String mobileNumber);

    Optional<Loan> findByLoanNumber(String loanNumber);
}
