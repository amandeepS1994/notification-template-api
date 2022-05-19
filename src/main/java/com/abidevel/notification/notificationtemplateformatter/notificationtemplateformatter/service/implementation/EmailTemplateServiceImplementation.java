package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service.implementation;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.configuration.TemplateFilesConfiguration;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.enumeration.TemplateType;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request.NotificationRequest;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.request.TemplateRequest;
import com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.service.EmailTemplateService;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailTemplateServiceImplementation implements EmailTemplateService {

    private final TemplateFilesConfiguration loadTemplateFiles;
    private final TemplateEngine templateEngine;

    public EmailTemplateServiceImplementation(TemplateFilesConfiguration loadTemplateFiles, TemplateEngine templateEngine) {
        this.loadTemplateFiles = loadTemplateFiles;
        this.templateEngine = templateEngine;
    }

    @Override
    public Optional<String> prepareEmailTemplate(NotificationRequest notificationRequest) {

        Map<String, Object> notificationParametersMap =
            notificationRequest.getNotificationParameters().stream()
            .collect(Collectors
            .toMap(TemplateRequest::getNotificationParameterName, TemplateRequest::getNotificationParameterValue));

        Context context = new Context();
        context.setVariables(notificationParametersMap);

        try {
            return contentMessage(notificationRequest.getNotificationTemplateName(), context);
        } catch (IOException e) {
            log.error("Failed to parse template.");
        }
 
    return Optional.empty();
}

private Optional<String> contentMessage (TemplateType templateType, Context context) throws IOException {
    if (templateType.equals(TemplateType.PHONENUMBERCHANGED)) {
        return Optional.of(templateEngine.process(this.loadTemplateFiles.getPhoneNumberChange().getFilename(), context));
    }

    if (templateType.equals(TemplateType.VIEWBALANCE)) {
        return Optional.of(templateEngine.process(this.loadTemplateFiles.getViewBalance().getURI().getPath(), context));
    }
    return Optional.empty();
}


}