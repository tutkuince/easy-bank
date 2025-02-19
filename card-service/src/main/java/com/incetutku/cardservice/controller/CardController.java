package com.incetutku.cardservice.controller;

import com.incetutku.cardservice.constants.CardConstants;
import com.incetutku.cardservice.dto.CardContactInfoDto;
import com.incetutku.cardservice.dto.CardDto;
import com.incetutku.cardservice.dto.ErrorResponseDto;
import com.incetutku.cardservice.dto.ResponseDto;
import com.incetutku.cardservice.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CardController {

    private final ICardService cardService;
    private final Environment environment;
    private final CardContactInfoDto cardContactInfoDto;

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Value("${build.version:0}")
    private String buildVersion;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        cardService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card By 'MobileNumber' REST API",
            description = "REST API to fetch card details by mobile number inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(
            @RequestHeader("easybank-correlation-id") String correlationId,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        logger.debug("easybank-correlation-id found:{}", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(cardService.fetchLoanByMobileNumber(mobileNumber));
    }

    @Operation(
            summary = "Update Card By 'MobileNumber' REST API",
            description = "REST API to update card details by mobile number inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto) {
        boolean isUpdated = cardService.updateLoan(cardDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete Card Details By 'MobileNumber' REST API",
            description = "REST API to update card details by mobile number inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteCardDetailsByMobileNumber(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        boolean isDeleted = cardService.deleteLoanByMobileNumber(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
    }

    @Operation(
            summary = "Get Build Information REST API",
            description = "Get Build information that is deployed into card service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Get Java Version Information REST API",
            description = "Get Java Version information that is deployed into card service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Information REST API",
            description = "Get Contact information that is deployed into card service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<CardContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(cardContactInfoDto);
    }
}
