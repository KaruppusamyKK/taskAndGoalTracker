package com.trackIt.api.controller;
import com.trackIt.api.dto.response.NotificationResponse;
import com.trackIt.api.dto.response.ResponseHandler;
import com.trackIt.api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notify")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping("/getNotificationCount")
    public ResponseEntity<?> getNotificationCount(@RequestParam String user) {
        return ResponseHandler.handleResponse(() -> notificationService.getNotificationCount(user));
    }

    @GetMapping("/getNotifications")
    public ResponseEntity<List<NotificationResponse>> getNotifications(@RequestParam String user) {
        return ResponseEntity.ok(notificationService.getUserNotifications(user));
    }


}
