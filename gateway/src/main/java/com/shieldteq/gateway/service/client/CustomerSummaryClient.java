package com.shieldteq.gateway.service.client;

import com.shieldteq.gateway.dtos.CustomerDTO;
import com.shieldteq.gateway.dtos.OrderDTO;
import com.shieldteq.gateway.dtos.PaymentDTO;
import com.shieldteq.gateway.dtos.response.ResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomerSummaryClient {


    @Deprecated
    @GetExchange(value = "/st/customer/customers", accept = MediaType.APPLICATION_JSON_VALUE)
    Mono<List<CustomerDTO>> getCustomerListDeprecated(@RequestParam("customerNumber") String customerNumber);

    @Deprecated
    @GetExchange(value = "/st/customer/customers/byCustomerNumber", accept = MediaType.APPLICATION_JSON_VALUE)
    Mono<CustomerDTO> getCustomerDetailsDeprecated(@RequestParam("customerNumber") String customerNumber);

    @GetExchange(value = "/st/customer/customers/{customerNumber}", accept = MediaType.APPLICATION_JSON_VALUE)
    Mono<CustomerDTO> getCustomerDetails(@PathVariable("customerNumber") String customerNumber);

    @GetExchange(value = "/st/order-service/orders/all/{customerNumber}", accept = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseDTO<List<OrderDTO>>> getCustomerOrders(@PathVariable("customerNumber") String customerNumber);

    @GetExchange(value = "/st/payment/payment/byCustomerNumber/{customerNumber}", accept = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseDTO<List<PaymentDTO>>> getCustomerPayments(@PathVariable("customerNumber") String customerNumber);
}
