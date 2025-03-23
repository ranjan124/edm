package com.shieldteq.customer.cqrs.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateCustomerCommand {
    @TargetAggregateIdentifier
    private String customerNumber;
    private String name;
    private String email;
    private String phone;
    private boolean active;
}

