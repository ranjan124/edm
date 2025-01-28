package com.shieldteq.inventory.audit

import io.micrometer.common.lang.NonNullApi
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@NonNullApi
@Component("auditAwareImpl")
class AuditAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> = Optional.of("ADMIN")
}