package com.shieldteq.customer.mappers;

import com.shieldteq.customer.dtos.CustomerDTO;
import com.shieldteq.customer.entities.Customer;

public final class CustomerDTOMapper {
    private CustomerDTOMapper() {
    }

    public static CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getCustomerNumber(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

}
