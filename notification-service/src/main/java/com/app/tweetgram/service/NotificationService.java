package com.app.tweetgram.service;

import com.app.tweetgram.dto.response.notification.NotificationUserResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.repository.projection.NotificationInfoProjection;
import com.app.tweetgram.repository.projection.NotificationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {

    Page<NotificationProjection> getUserNotifications(Pageable pageable);

    Page<TweetResponse> getUserMentionsNotifications(Pageable pageable);

    List<NotificationUserResponse> getTweetAuthorsNotifications();

    NotificationInfoProjection getUserNotificationById(Long notificationId);

    Page<TweetResponse> getNotificationsFromTweetAuthors(Pageable pageable);
}
