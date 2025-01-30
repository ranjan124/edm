package com.shieldteq.gateway.service.client.handler;

import com.shieldteq.gateway.dtos.*;
import com.shieldteq.gateway.dtos.response.ResponseDTO;
import com.shieldteq.gateway.service.client.CustomerSummaryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CustomerCompositeHandler {
    private final CustomerSummaryClient customerSummaryClient;

    CustomerCompositeHandler(CustomerSummaryClient customerSummaryClient) {
        this.customerSummaryClient = customerSummaryClient;
    }

    @Deprecated
    public Mono<ServerResponse> getCustomerDetailsDeprecated(ServerRequest request) {
        String customerNumber = request.queryParam("customerNumber").orElseThrow(() -> new IllegalArgumentException("customerNumber is required"));
        Mono<CustomerDTO> customerDetails = customerSummaryClient.getCustomerDetailsDeprecated(customerNumber);
        Mono<List<CustomerDTO>> customerDetails2 = customerSummaryClient.getCustomerListDeprecated(customerNumber);

        return Mono.zip(customerDetails, customerDetails2).flatMap(tuple -> {
            CustomerDTO customer = tuple.getT1();
            List<CustomerDTO> customer2 = tuple.getT2();
            Summary summary = new Summary(customer, customer2);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(summary);
        });
    }

    public Mono<ServerResponse> getCustomerDetails(ServerRequest request) {
        String customerNumber = request.pathVariable("customerNumber");
        Mono<CustomerDTO> customerDetails = customerSummaryClient.getCustomerDetails(customerNumber);
        Mono<ResponseDTO<List<OrderDTO>>> customerOrders = customerSummaryClient.getCustomerOrders(customerNumber);
        Mono<ResponseDTO<List<PaymentDTO>>> payments = customerSummaryClient.getCustomerPayments(customerNumber);

        return Mono.zip(customerDetails, customerOrders, payments).flatMap(tuple -> {
            CustomerDTO customer = tuple.getT1();
            List<OrderDTO> orderDTOList = tuple.getT2().getData();
            List<PaymentDTO> paymentList = tuple.getT3().getData();
            CustomerSummary summary = new CustomerSummary(customer, orderDTOList, paymentList);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(summary);
        });
    }


}
