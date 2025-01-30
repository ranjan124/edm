package com.shieldteq.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/st/customer/**")
                        .filters(f -> f.rewritePath("/st/customer/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://CUSTOMER"))
                .route(p -> p.path("/st/inventory/**")
                        .filters(f -> f.rewritePath("/st/inventory/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://INVENTORY"))
                .route(p -> p.path("/st/order-service/**")
                        .filters(f -> f.rewritePath("/st/order-service/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://ORDER-SERVICE"))
                .route(p -> p.path("/st/payment/**")
                        .filters(f -> f.rewritePath("/st/payment/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://PAYMENT"))
                .route(p -> p.path("/st/shipping/**")
                        .filters(f -> f.rewritePath("/st/shipping/?(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://SHIPPING"))
                .build();
    }

}
