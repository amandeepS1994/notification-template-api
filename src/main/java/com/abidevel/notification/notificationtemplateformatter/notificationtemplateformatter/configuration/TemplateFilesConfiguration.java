package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class TemplateFilesConfiguration {
    @Value("classpath:templates/PhoneNumberChanged.html")
    private Resource phoneNumberTemplate;
    @Value("classpath:templates/ViewBalance.html")
    private Resource viewBalanceTemplate;

    @Bean()
    public Resource getViewBalance() {
        return viewBalanceTemplate;
    }

    @Bean()
    public Resource getPhoneNumberChange() {
        return phoneNumberTemplate;
    }
}
