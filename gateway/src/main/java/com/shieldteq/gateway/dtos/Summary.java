package com.shieldteq.gateway.dtos;

import java.util.List;

@Deprecated
public class Summary {
    private CustomerDTO customerDTO;
    private List<CustomerDTO> customerDTO2;
//    OrderDTO orderDTO;
//    PaymentDTO paymentDTO;


    public Summary(CustomerDTO customerDTO, List<CustomerDTO> customerDTO2) {
        this.customerDTO = customerDTO;
        this.customerDTO2 = customerDTO2;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<CustomerDTO> getCustomerDTO2() {
        return customerDTO2;
    }

    public void setCustomerDTO2(List<CustomerDTO> customerDTO2) {
        this.customerDTO2 = customerDTO2;
    }
}