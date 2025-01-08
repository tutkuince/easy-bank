package com.incetutku.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountDto {

    @Schema(
            description = "Account Number of Easy Bank Account"
    )
    @NotEmpty(message = "Account Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Easy Bank Account",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type cannot be a null or empty")
    private String accountType;

    @Schema(
            description = "Easy Bank branch address",
            example = "123 Izmir"
    )
    @NotEmpty(message = "Branch Address cannot be a null or empty")
    private String branchAddress;
}
