package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private String emailSubject;
    private String emailContent;
    private String smsContent;

}