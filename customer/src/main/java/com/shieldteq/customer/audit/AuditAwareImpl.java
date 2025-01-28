package com.shieldteq.customer.audit;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NonNullApi
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ADMIN");
    }
}
