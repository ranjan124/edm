package com.shieldteq.customer.cqrs.event;

import lombok.Data;

@Data
public class CustomerUpdatedEvent {
    private String customerNumber;
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private boolean active;
}
