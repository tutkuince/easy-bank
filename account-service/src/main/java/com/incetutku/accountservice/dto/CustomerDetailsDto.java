package com.incetutku.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Customer Details",
        description = "Schema to hold Customer, Account, Card and Loan Information"
)
@Data
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the Customer",
            example = "Tutku Ince"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Schema(
            description = "Email of the Customer",
            example = "tince@mail.com"
    )
    @NotEmpty(message = "Email Address cannot be null or empty")
    @Email(message = "Email Address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer",
            example = "5555555555"
    )
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDto accountDto;

    @Schema(
            description = "Card(s) details of the Customer"
    )
    private CardDto cardDto;

    @Schema(
            description = "Loan(s) details of the Customer"
    )
    private LoanDto loanDto;
}
