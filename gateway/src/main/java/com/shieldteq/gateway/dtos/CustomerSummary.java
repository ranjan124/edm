package com.shieldteq.gateway.dtos;

import java.util.List;

public class CustomerSummary {
    private final List<PaymentDTO> payments;
    private CustomerDTO customerDTO;
    private List<OrderDTO> orders;

    public CustomerSummary(CustomerDTO customerDTO, List<OrderDTO> orders, List<PaymentDTO> payments) {
        this.customerDTO = customerDTO;
        this.orders = orders;
        this.payments = payments;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }
}