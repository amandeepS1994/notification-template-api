package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.controller;

import java.util.Optional;

import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.NotificationResponse;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.enumeration.NotificationType;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request.NotificationRequest;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.response.ApiResponse;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service.EmailTemplateService;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service.SMSTemplateService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "notification/template/")
public class TemplateController {

    private final EmailTemplateService emailTemplateService;
    private final SMSTemplateService smsTemplateService;


    public TemplateController(EmailTemplateService emailTemplateService, SMSTemplateService smsTemplateService) {
        this.emailTemplateService = emailTemplateService;
        this.smsTemplateService = smsTemplateService;
    }



    @GetMapping()
    public ResponseEntity<ApiResponse<NotificationResponse>> retrieveCommunicationTemplate(@RequestBody NotificationRequest notificationRequest) {
        if (notificationRequest.getNotificationMode().equals(NotificationType.EMAIL)) {
            Optional<String> emailContent = emailTemplateService.prepareEmailTemplate(notificationRequest);
            if (emailContent.isPresent()) {
                return new ResponseEntity<>(new ApiResponse<>(true, NotificationResponse.builder()
                .emailContent(emailContent.get())
                .build()), HttpStatus.ACCEPTED);
            }
        }

        if (notificationRequest.getNotificationMode().equals(NotificationType.SMS)) { 
            Optional<String> smsContent = smsTemplateService.prepareSMSTemplate(notificationRequest);
            if (smsContent.isPresent()) {
                return new ResponseEntity<>(new ApiResponse<>(true, NotificationResponse.builder().smsContent(smsContent.get()).build()), HttpStatus.ACCEPTED);
            }
        }

        return new ResponseEntity<>(new ApiResponse<>(false, "Invalid Request"), HttpStatus.BAD_REQUEST);
    }
    
}
