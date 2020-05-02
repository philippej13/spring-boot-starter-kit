package com.company.appli.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class AMServiceConfiguration {
    @Value("${am.management.baseUri}")
    private String amManagementBaseUri;

    @Value("${am.management.adminLogin}")
    private String amManagementAdminLogin;

    @Value("${am.management.adminPassword}")
    private String amManagementAdminPassword;

    public String getAmManagementBaseUri() {
        return amManagementBaseUri;
    }

    public String getAmManagementAdminLogin() {
        return amManagementAdminLogin;
    }

    public String getAmManagementAdminPassword() {
        return amManagementAdminPassword;
    }

}
