package com.incetutku.accountservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {
    @NotEmpty(message = "Account Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be a null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be a null or empty")
    private String branchAddress;
}
