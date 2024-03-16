package com.app.tweetgram.service;

import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.model.Notification;

public interface NotificationClientService {

    NotificationResponse sendNotification(Notification notification, boolean notificationCondition);

    void sendTweetMentionNotification(Notification notification);

    void sendTweetNotificationToSubscribers(Long tweetId);
}
