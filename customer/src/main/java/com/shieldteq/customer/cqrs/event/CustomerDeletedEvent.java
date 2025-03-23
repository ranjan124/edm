package com.shieldteq.customer.cqrs.event;

import lombok.Data;

@Data
public class CustomerDeletedEvent {
    private String customerNumber;
    private String customerId;
    private boolean active;
}
