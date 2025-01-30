package com.shieldteq.gateway.router;

import com.shieldteq.gateway.service.client.handler.CustomerCompositeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class CustomerCompositeRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CustomerCompositeHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/deprecated/composite/fetchCustomerSummary")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        .and(RequestPredicates.queryParam("customerNumber", p -> true)),
                handler::getCustomerDetailsDeprecated)

                .andRoute(RequestPredicates.GET("/composite/customerDetails/{customerNumber}"),
                handler::getCustomerDetails);
    }
}
