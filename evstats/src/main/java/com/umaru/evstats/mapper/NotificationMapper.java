package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.NotificationDto;
import com.umaru.evstats.entity.Notification;

public class NotificationMapper {
    public static NotificationDto mapToNotificationDto(Notification notification) {
        NotificationDto notificationDto = NotificationDto.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .notifications(notification.getNotifications())
                .createdAt(notification.getCreatedAt())
                .build();
        return notificationDto;
    }

    public static Notification mapToNotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .id(notificationDto.getId())
                .userId(notificationDto.getUserId())
                .notifications(notificationDto.getNotifications())
                .createdAt(notificationDto.getCreatedAt())
                .build();
        return notification;
    }
}
