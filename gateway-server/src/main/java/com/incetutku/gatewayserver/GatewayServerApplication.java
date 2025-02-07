package com.incetutku.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/easybank/account-service/**")
                        .filters(f -> f.rewritePath("/easybank/accounts/(?<segment>.*)","/${segment}")
                                .addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("accountCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p.path("/easybank/card-service/**")
                        .filters(f -> f.rewritePath("/easybank/card-service/(?<segment>.*)","/${segment}")
                                .addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))
                        .uri("lb://CARD-SERVICE"))
                .route(p -> p.path("/easybank/loans-service/**")
                        .filters(f -> f.rewritePath("/easybank/loans-service/(?<segment>.*)","/${segment}")
                                .addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNT-SERVICE"))
                .build();
    }
}
