package com.shieldteq.customer.services;

import com.shieldteq.customer.dtos.CustomerCreateDTO;
import com.shieldteq.customer.dtos.CustomerDTO;
import com.shieldteq.customer.dtos.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerCreateDTO dto);

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomer(String customerNumber);

    String deleteCustomer(String id);

    CustomerDTO updateCustomer(String customerNumber, CustomerUpdateDTO dto);
}
