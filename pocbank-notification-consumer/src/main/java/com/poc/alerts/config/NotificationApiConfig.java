package com.poc.alerts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationApiConfig {

    @Value("${notification.api.token}")
    private String apiToken;

    public String getApiToken() {
        return apiToken;
    }
}