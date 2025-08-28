package com.nipa.sslcommerzexample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sslcommerz")
public class SSLCommerzConfig {
    private String storeId;
    private String storePassword;
    private boolean sandbox;

    // Getters and Setters
    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }
    public String getStorePassword() { return storePassword; }
    public void setStorePassword(String storePassword) { this.storePassword = storePassword; }
    public boolean isSandbox() { return sandbox; }
    public void setSandbox(boolean sandbox) { this.sandbox = sandbox; }
}