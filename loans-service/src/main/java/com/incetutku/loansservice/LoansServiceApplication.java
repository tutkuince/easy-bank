package com.incetutku.loansservice;

import com.incetutku.loansservice.dto.LoanContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = {LoanContactInfoDto.class})
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Loan Microservice REST API Documentation",
                description = "EasyBank Loan Microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Tutku Ince",
                        email = "tutku.ince@outlook.com",
                        url = "https://github.com/tutkuince"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/tutkuince"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EasyBank Loan Service REST API Documentation",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class LoansServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansServiceApplication.class, args);
    }

}
