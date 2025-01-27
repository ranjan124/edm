package com.shieldteq.customer.services.impl;

import com.shieldteq.customer.dtos.CustomerCreateDTO;
import com.shieldteq.customer.dtos.CustomerDTO;
import com.shieldteq.customer.dtos.CustomerUpdateDTO;
import com.shieldteq.customer.entities.Customer;
import com.shieldteq.customer.entities.CustomerIdRecord;
import com.shieldteq.customer.exceptions.CustomerException;
import com.shieldteq.customer.mappers.CustomerDTOMapper;
import com.shieldteq.customer.repositories.CustomerIdRecordRepository;
import com.shieldteq.customer.repositories.CustomerRepository;
import com.shieldteq.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerIdRecordRepository customerIdRecordRepository;

    @Override
    public CustomerDTO createCustomer(@Valid CustomerCreateDTO dto) {

        customerIdRecordRepository.findByEmailOrPhone(dto.email(), dto.phone()).ifPresent(c -> {
            throw new CustomerException("Customer already exists", "/create");
        });
        CustomerIdRecord customerIdRecord = new CustomerIdRecord();
        customerIdRecord.setEmail(dto.email());
        customerIdRecord.setPhone(dto.phone());
        Long customerNumber = customerIdRecordRepository.save(customerIdRecord).getId();
        Customer newCustomer = new Customer();
        newCustomer.setCustomerNumber(String.valueOf(customerNumber));
        newCustomer.setName(dto.name());
        newCustomer.setEmail(dto.email());
        newCustomer.setPhone(dto.phone());
        newCustomer.setActive(true);
        return CustomerDTOMapper.toDTO(customerRepository.save(newCustomer));
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAllByActiveIsTrue().stream().map(CustomerDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber).map(CustomerDTOMapper::toDTO).orElseThrow(() -> new CustomerException("Customer not found", "/" + customerNumber));
    }

    @Override
    public String deleteCustomer(String customerNumber) {
        customerIdRecordRepository.findById(Long.parseLong(customerNumber)).ifPresentOrElse(customerIdRecordRepository::delete, () -> {
            throw new CustomerException("Customer already exists", "/create");
        });
        customerRepository.findByCustomerNumber(customerNumber).ifPresentOrElse(customer -> customerRepository.deleteByCustomerNumber(customerNumber), () -> {
            throw new CustomerException("Customer not found", "/" + customerNumber);
        });
        return customerNumber;
    }

    @Override
    public CustomerDTO updateCustomer(String customerNumber, CustomerUpdateDTO dto) {
        return customerRepository.findByCustomerNumber(customerNumber)
                .map(customer -> {
                    customer.setName(dto.name());
                    customer.setEmail(dto.email());
                    customer.setPhone(dto.phone());
                    return customerRepository.save(customer);
                })
                .map(CustomerDTOMapper::toDTO)
                .orElseThrow(() -> new CustomerException("Customer not found", "/" + customerNumber));
    }
}
