package com.incetutku.loansservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")
@Entity
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
