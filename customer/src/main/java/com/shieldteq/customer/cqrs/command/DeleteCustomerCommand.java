package com.shieldteq.customer.cqrs.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteCustomerCommand {
    @TargetAggregateIdentifier
    private String customerNumber;
    private String customerId;
    private boolean active;
}
