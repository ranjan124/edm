package com.shieldteq.customer.controllers;

import com.shieldteq.customer.Constants;
import com.shieldteq.customer.dtos.CustomerCreateDTO;
import com.shieldteq.customer.dtos.CustomerDTO;
import com.shieldteq.customer.dtos.CustomerUpdateDTO;
import com.shieldteq.customer.dtos.response.ErrorResponseDTO;
import com.shieldteq.customer.dtos.response.ResponseDTO;
import com.shieldteq.customer.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(
        name = "Customer REST endpoints",
        description = "Customer REST endpoints for CRUD operations"
)
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerService customerService;


    @Operation(
            summary = "Get all active customers"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            )}
    )
    @GetMapping
    public Flux<CustomerDTO> getCustomers() {
        return Flux.fromIterable(customerService.getCustomers());
    }

    @Operation(
            summary = "Create a new customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Customer already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )}
    )
    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseDTO<CustomerDTO>>> createCustomer(@Valid @RequestBody CustomerCreateDTO customerDTO) {
        CustomerDTO customer = customerService.createCustomer(customerDTO);
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(Constants.STATUS_201, Constants.STATUS_200_MESSAGE, customer)));
    }


    @Operation(
            summary = "Update an existing customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )}
    )
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

    @Operation(
            summary = "Get an existing customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            )}
    )
    @GetMapping("/{customerNumber}")
    public Mono<ResponseEntity<CustomerDTO>> getCustomer(
            @Valid
            @PathVariable
            @Pattern(regexp = "^\\d{7,}$", message = "Customer number is not valid")
            String customerNumber) {
        return Mono.just(ResponseEntity.ok(customerService.getCustomer(customerNumber)));
    }

    @Operation(
            summary = "Delete an existing customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Customer not found"
            )}
    )
    @DeleteMapping("/{customerNumber}")
    public Mono<ResponseEntity<ResponseDTO<String>>> deleteCustomer(
            @PathVariable
            @Pattern(regexp = "^\\d{7,}$", message = "Customer number is not valid")
            String customerNumber) {
        String cno = customerService.deleteCustomer(customerNumber);
        return Mono.just(ResponseEntity.ok(new ResponseDTO<>(Constants.STATUS_200, Constants.STATUS_200_MESSAGE, cno)));
    }
}
