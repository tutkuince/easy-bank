package com.incetutku.accountservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "account_type", length = 100, nullable = false)
    private String accountType;
    @Column(name = "branch_address", length = 200, nullable = false)
    private String branchAddress;
}
