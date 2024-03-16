package com.app.tweetgram.service.util;

import com.app.tweetgram.dto.response.notification.NotificationListResponse;
import com.app.tweetgram.dto.response.notification.NotificationTweetResponse;
import com.app.tweetgram.dto.response.notification.NotificationUserResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.feign.ListsClient;
import com.app.tweetgram.feign.TweetClient;
import com.app.tweetgram.feign.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceHelper {

    private final UserClient userClient;
    private final TweetClient tweetClient;
    private final ListsClient listsClient;

    public UserResponse getUserById(Long userId) {
        return userClient.getUserById(userId);
    }

    public TweetResponse getTweetById(Long tweetId) {
        return tweetClient.getTweetById(tweetId);
    }

    public NotificationUserResponse getNotificationUser(Long userId) {
        return userClient.getNotificationUser(userId);
    }

    public NotificationTweetResponse getNotificationTweet(Long userId) {
        return tweetClient.getNotificationTweet(userId);
    }

    public NotificationListResponse getNotificationList(Long listId) {
        return listsClient.getNotificationList(listId);
    }
}
