package com.shieldteq.customer.controllers;

import com.shieldteq.customer.Constants;
import com.shieldteq.customer.dtos.CustomerCreateDTO;
import com.shieldteq.customer.dtos.CustomerDTO;
import com.shieldteq.customer.dtos.CustomerUpdateDTO;
import com.shieldteq.customer.dtos.response.ResponseDTO;
import com.shieldteq.customer.services.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping
    public Flux<CustomerDTO> getCustomers() {
        return Flux.fromIterable(customerService.getCustomers());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseDTO<CustomerDTO>>> createCustomer(@Valid@RequestBody CustomerCreateDTO customerDTO) {
        CustomerDTO customer = customerService.createCustomer(customerDTO);
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(Constants.STATUS_201, Constants.STATUS_200_MESSAGE, customer)));
    }

    @PutMapping("/{customerNumber}")
    public Mono<ResponseEntity<ResponseDTO<CustomerDTO>>> updateCustomer(
            @RequestBody CustomerUpdateDTO customerDTO,
            @Valid
            @PathVariable
            @Pattern(regexp = "^\\d{7,}$", message = "Customer number is not valid")
            String customerNumber) {
        CustomerDTO customer = customerService.updateCustomer(customerNumber, customerDTO);
        return Mono.just(ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO<>(Constants.STATUS_200, Constants.STATUS_200_MESSAGE, customer)));
    }

    @GetMapping("/{customerNumber}")
    public Mono<ResponseEntity<CustomerDTO>> getCustomer(
            @Valid
            @PathVariable
            @Pattern(regexp = "^\\d{7,}$", message = "Customer number is not valid")
            String customerNumber) {
        return Mono.just(ResponseEntity.ok(customerService.getCustomer(customerNumber)));
    }

    @DeleteMapping("/{customerNumber}")
    public Mono<ResponseEntity<ResponseDTO<String>>> deleteCustomer(
            @PathVariable
            @Pattern(regexp = "^\\d{7,}$", message = "Customer number is not valid")
            String customerNumber) {
        String cno = customerService.deleteCustomer(customerNumber);
        return Mono.just(ResponseEntity.ok(new ResponseDTO<>(Constants.STATUS_200, Constants.STATUS_200_MESSAGE, cno)));
    }
}
