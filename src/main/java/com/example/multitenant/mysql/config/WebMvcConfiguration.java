package com.example.multitenant.mysql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final HandlerInterceptor tenantIdInterceptor;

    public WebMvcConfiguration(HandlerInterceptor tenantIdInterceptor) {
        this.tenantIdInterceptor = tenantIdInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantIdInterceptor);
    }
}