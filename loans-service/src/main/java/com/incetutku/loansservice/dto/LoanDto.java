package com.incetutku.loansservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name = "Loan",
        description = "Schema to hold Loan information"
)
public class LoanDto {
    @Schema(
            description = "Mobile Number of Customer",
            example = "5555555555"
    )
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Loan Number of Customer",
            example = "555555555555"
    )
    @NotEmpty(message = "Loan Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "LoanNumber must be 12 digits")
    private String loanNumber;
    private String loanType;

    @PositiveOrZero(
            message = "Total loan amount should be equal or greater than zero"
    )
    @Schema(
            description = "Total loan amount again a loan",
            example = "1000"
    )
    private int totalLoan;

    @PositiveOrZero(
            message = "Total loan amount paid should be equal or greater than zero"
    )
    @Schema(
            description = "Total loan amount paid again a loan",
            example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(
            message = "Total outstanding amount should be equal or greater than zero"
    )
    @Schema(
            description = "Total outstanding amount again a loan",
            example = "99000"
    )
    private int outstandingAmount;
}
