package com.example.product.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("server-mall", p -> p.path("/api/server-mall/v1/**")
                        .uri("http://localhost:8000"))
                .route("server-oauth", p -> p.path("/api/server-oauth/v1/**")
                        .uri("http://localhost:8001"))
                .build();
    }

}