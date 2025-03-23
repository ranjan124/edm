package com.shieldteq.customer.cqrs.event;

import lombok.Data;

@Data
public class CustomerCreatedEvent {
    private String customerNumber;
    private String name;
    private String email;
    private String phone;
    private boolean active;
}
