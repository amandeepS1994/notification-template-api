package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.enumeration.TemplateType;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request.NotificationRequest;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request.TemplateRequest;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service.SMSTemplateService;

import org.springframework.stereotype.Service;

@Service
public class SMSTemplateServiceImplementation implements SMSTemplateService{

    @Override
    public Optional<String> prepareSMSTemplate(NotificationRequest notificationRequest) {
        return retrieveTemplateMessage(notificationRequest);
    }

    private Optional<String> retrieveTemplateMessage(NotificationRequest notificationRequest) {
        Map<String, String> properties = notificationRequest.getNotificationParameters().stream()
        .collect(Collectors.toMap(TemplateRequest::getNotificationParameterName, TemplateRequest::getNotificationParameterValue));
        return Optional.of(notificationRequest.getNotificationTemplateName().equals(TemplateType.PHONENUMBERCHANGED) ? this.phoneNumberChanged(properties) : this.retrieveViewBalanceSMSType(properties));
    }

    private String retrieveViewBalanceSMSType (Map<String, String> viewBalanceProperties) {
        if (Objects.nonNull(viewBalanceProperties) && viewBalanceProperties.containsKey("name") && viewBalanceProperties.containsKey("account")) {
            return new StringBuilder()
                .append(String.format("Hello %s %n", viewBalanceProperties.get("name")))
                .append("Welcome to Citizen Bank %n %n")
                .append(String.format("Your balance for the account number ending with %s is balance as of £124,00 as of %s %n %n", viewBalanceProperties.get("account"), new SimpleDateFormat("dd/mm/yyyy").format(new Date())))
                .append("You can login to internet banking channel to view more details on the account \n \n")
                .append("Regards, \n")
                .append("Citizen Bank \n")
                .append("United States")
                .toString();
        }
        return null;
    }

    private String phoneNumberChanged(Map<String, String> changedNumberProperties) {
        if (Objects.nonNull(changedNumberProperties) && changedNumberProperties.containsKey("oldPhoneNumber") && changedNumberProperties.containsKey("newPhoneNumber")) {
            return new StringBuilder()
            .append(String.format("Hello %s %n", changedNumberProperties.get("name")))
            .append("Welcome to Citizen Bank %n %n")
            .append(String.format("Your Phone number is changed from %s to %s as of %s %n %n", changedNumberProperties.get("oldPhoneNumber"), changedNumberProperties.get("newPhoneNumber"), new SimpleDateFormat("dd/mm/yyyy").format(new Date())))
            .append("You can login to internet banking channel to view more details on the account \n \n")
            .append("Regards, \n")
            .append("Citizen Bank \n")
            .append("United States")
            .toString();
        }
       return null;
    }

    

}
