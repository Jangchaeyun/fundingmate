package com.fund.fundingmate.domain.notification.repository;

import com.fund.fundingmate.domain.notification.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface notificationRepository extends JpaRepository<Notifications, Long> {
}
