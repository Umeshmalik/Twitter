package com.app.tweetgram.dto.response.notification;

import com.app.tweetgram.enums.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private Long id;
    private LocalDateTime date;
    private NotificationType notificationType;
    private Long notifiedUserId;
    private NotificationUserResponse user;
    private NotificationUserResponse userToFollow;
    private NotificationTweetResponse tweet;
    private NotificationListResponse list;
    private boolean isAddedToList;
}
