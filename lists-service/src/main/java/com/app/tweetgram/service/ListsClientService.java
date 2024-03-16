package com.app.tweetgram.service;

import com.app.tweetgram.dto.response.tweet.TweetListResponse;
import com.app.tweetgram.dto.response.notification.NotificationListResponse;

public interface ListsClientService {

    NotificationListResponse getNotificationList(Long listId);

    TweetListResponse getTweetList(Long listId);
}
