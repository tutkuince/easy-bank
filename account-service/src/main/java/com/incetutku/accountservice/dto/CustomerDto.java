package com.incetutku.accountservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @NotEmpty(message = "Email Address cannot be null or empty")
    @Email(message = "Email Address should be a valid value")
    private String email;

    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
    private String mobileNumber;
    private AccountDto accountDto;
}
