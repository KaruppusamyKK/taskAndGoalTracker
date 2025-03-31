package com.trackIt.api.repository;

import com.trackIt.api.dto.response.NotificationResponse;
import com.trackIt.api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findBySender(String user);
}
