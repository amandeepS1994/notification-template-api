package com.abidevel.notification.notificationtemplateformatter.notificationtemplateformatter.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> {
    private boolean isSuccessful;
    private String message;
    private T data;
    private LocalDateTime localDate;

    public ApiResponse (boolean success, String message) {
        this.isSuccessful = success;
        this.message = message;
        this.localDate = LocalDateTime.now();
    }

    public ApiResponse (boolean success, T data) {
        this.isSuccessful = success;
        this.data = data;
        this.localDate = LocalDateTime.now();
    }

}
