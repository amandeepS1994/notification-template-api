package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service;

import java.util.Optional;

import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request.NotificationRequest;

public interface EmailTemplateService {
    Optional<String> prepareEmailTemplate (NotificationRequest notificationRequest);
}
