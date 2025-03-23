package com.shieldteq.customer.dtos;

import java.util.UUID;

public sealed interface Something {
    record Reduce(UUID id) implements Something {
    }
}
