package com.incetutku.cardservice;

import com.incetutku.cardservice.dto.CardContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = {CardContactInfoDto.class})
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Card Microservice REST API Documentation",
                description = "EasyBank Card Microservice REST API Documentation",
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
                description = "EasyBank Card Service REST API Documentation",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

}
