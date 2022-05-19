package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TemplateRequest {
    private String notificationParameterName;
    private String notificationParameterValue;


    public String getNotificationParameterName() {
        return this.notificationParameterName;
    }

    public void setNotificationParameterName(String notificationParameterName) {
        this.notificationParameterName = notificationParameterName;
    }

    public String getNotificationParameterValue() {
        return this.notificationParameterValue;
    }

    public void setNotificationParameterValue(String notificationParameterValue) {
        this.notificationParameterValue = notificationParameterValue;
    }

}
