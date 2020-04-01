package com.example.multitenant.mysql.jdbc.connections;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TenantDatabaseResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentTenantIdentifier = TenantContext.getCurrentTenant();
        return StringUtils.isEmpty(currentTenantIdentifier) ? TenantContext.DEFAULT_TENANT_IDENTIFIER : currentTenantIdentifier;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
