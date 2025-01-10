package com.incetutku.cardservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Card",
        description = "Schema to hold Card Information"
)
@Data
public class CardDto {
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer",
            example = "5555555555"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must be 12 digits")
    @Schema(
            description = "Card Number of Customer",
            example = "555555555555"
    )
    private String cardNumber;

    @NotEmpty(message = "Card Type cannot be null or empty")
    @Schema(
            description = "Type of the Card",
            example = "Credit Card"
    )
    private String cardType;

    @Positive(
            message = "Total card limit should be greater than zero"
    )
    @Schema(
            description = "Total amount limit available against a card",
            example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(
            message = "Used amount should be equal to zero or greater than zero"
    )
    @Schema(
            description = "Used amount available against a card",
            example = "100000"
    )
    private int amountUsed;

    @PositiveOrZero(
            message = "Available amount should be equal to zero or greater than zero"
    )
    @Schema(
            description = "Available amount available against a card",
            example = "100000"
    )
    private int availableAmount;
}
