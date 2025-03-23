package com.shieldteq.customer.cqrs.query;

import lombok.Value;

@Value
public class FindCustomerQuery {
    private final String customerNumber;
}
