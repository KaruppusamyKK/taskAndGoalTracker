package com.trackIt.api.service;

import com.trackIt.api.dto.request.AssigneeRequest;
import com.trackIt.api.dto.response.NotificationResponse;
import com.trackIt.api.exception.UserNotFoundException;
import com.trackIt.api.model.Notification;
import com.trackIt.api.model.User;
import com.trackIt.api.repository.NotificationRepository;
import com.trackIt.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;



    public void saveUserNotifications(AssigneeRequest notificationRequest) {
        Notification notification = Notification.builder()
                .taskName(notificationRequest.taskName())
                .description(notificationRequest.description())
                .Message("Sample message")
                .sender(notificationRequest.username())
                .build();
        notificationRepository.save(notification);
        logger.info("Notification saved successfully");
    }

    public List<NotificationResponse> getUserNotifications(String user) {
        List<NotificationResponse> notificationResponses = new ArrayList<>();
        notificationRepository.findBySender(user).forEach(notification -> {
                    NotificationResponse notificationResponse = new
                            NotificationResponse(notification.getTaskName(),
                            notification.getDescription(),notification.getSender(),
                            null,notification.getMessage(),notification.getTimestamp());
            notificationResponses.add(notificationResponse);
                });
        return notificationResponses;

    }


}
