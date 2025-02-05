package com.incetutku.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

//@Configuration
public class RouteLocatorConfig {

    /*@Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/easybank/api/v1/accounts/**")
                        .filters(f -> f.rewritePath("/easybank/api/v1/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p.path("/easybank/api/v1/cards/**")
                        .filters(f -> f.rewritePath("/easybank/api/v1/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARD-SERVICE"))
                .route(p -> p.path("/easybank/api/v1/loans/**")
                        .filters(f -> f.rewritePath("/easybank/api/v1/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS-SERVICE")).build();
    }*/
}
