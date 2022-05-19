package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request;

import java.util.List;

import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.enumeration.NotificationType;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.enumeration.TemplateType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private List<TemplateRequest> notificationParameters;
    private TemplateType notificationTemplateName;
    private NotificationType notificationMode;

    
}
