package com.example.multitenant.mysql.jdbc.connections;

public class TenantContext {
    public static final String DEFAULT_TENANT_IDENTIFIER = "mysql_master";
    private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    private TenantContext() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.remove();
    }
}
