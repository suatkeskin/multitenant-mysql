package com.example.multitenant.mysql.jdbc.connections;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider implements ServiceRegistryAwareService {
    private static final Map<String, ConnectionProvider> connectionProviderMap = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        final Map<String, Object> properties = serviceRegistry.getService(ConfigurationService.class).getSettings();
        final HikariDataSource dataSource = (HikariDataSource) properties.get(AvailableSettings.DATASOURCE);
        final Map<String, Object> connectionProperties = new HashMap<>();
        connectionProperties.put(AvailableSettings.URL, dataSource.getJdbcUrl());
        connectionProperties.put(AvailableSettings.USER, dataSource.getUsername());
        connectionProperties.put(AvailableSettings.PASS, dataSource.getPassword());
        initConnectionProvider(TenantContext.DEFAULT_TENANT_IDENTIFIER, connectionProperties);
        initConnectionProviderForTenants(dataSource);
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return connectionProviderMap.get(TenantContext.DEFAULT_TENANT_IDENTIFIER);
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
        return connectionProviderMap.get(tenantIdentifier);
    }

    private void initConnectionProviderForTenants(final HikariDataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            try (ResultSet resultSet = connection.prepareStatement("SELECT * FROM datasource_config").executeQuery()) {
                while (resultSet.next()) {
                    String driverClassName = resultSet.getString(2);
                    String url = resultSet.getString(3);
                    String tenantIdentifier = resultSet.getString(4);
                    String username = resultSet.getString(5);
                    String pass = resultSet.getString(6);
                    Map<String, Object> properties = new HashMap<>();
                    properties.put(AvailableSettings.DRIVER, driverClassName);
                    properties.put(AvailableSettings.URL, url);
                    properties.put(AvailableSettings.USER, username);
                    properties.put(AvailableSettings.PASS, pass);
                    initConnectionProvider(tenantIdentifier, properties);
                }
            }
        } catch (SQLException e) {
            throw new HibernateException("Exception while auto discovering tenants.", e);
        }
    }

    private void initConnectionProvider(String tenantId, Map<String, Object> properties) {
        final HikariConnectionProvider connectionProvider = new HikariConnectionProvider();
        connectionProvider.configure(properties);
        connectionProviderMap.put(tenantId, connectionProvider);
    }
}