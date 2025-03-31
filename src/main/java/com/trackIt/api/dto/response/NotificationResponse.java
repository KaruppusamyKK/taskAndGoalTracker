package com.trackIt.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Builder
@Getter
@Setter
public class NotificationResponse {

    private String taskName;

    private String description;

    private String sender;

    private String action;

    private String message;

    private LocalDateTime timestamp;


    public NotificationResponse(String taskName, String description, String sender, String action, String message, LocalDateTime timestamp) {
        this.taskName = taskName;
        this.description = description;
        this.sender = sender;
        this.action = action;
        this.message = message;
        this.timestamp = timestamp;
    }
}
