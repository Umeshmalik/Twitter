package com.app.tweetgram.controller.api;

import com.app.tweetgram.dto.request.NotificationRequest;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.feign.WebSocketClient;
import com.app.tweetgram.mapper.NotificationClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.app.tweetgram.constants.PathConstants.*;
import static com.app.tweetgram.constants.WebsocketConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_NOTIFICATION)
public class NotificationApiController {

    private final NotificationClientMapper notificationClientMapper;
    private final WebSocketClient webSocketClient;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest request) {
        NotificationResponse notification = notificationClientMapper.sendNotification(request);
        sendTopicNotification(notification, notification.getNotifiedUserId());
    }

    @PostMapping(TWEET)
    public NotificationResponse sendTweetNotification(@RequestBody NotificationRequest request) {
        NotificationResponse notification = notificationClientMapper.sendNotification(request);
        sendTopicNotification(notification, notification.getTweet().getAuthorId());
        webSocketClient.send(TOPIC_FEED, notification);
        webSocketClient.send(TOPIC_TWEET + notification.getTweet().getId(), notification);
        return notification;
    }

    @PostMapping(MENTION)
    public void sendTweetMentionNotification(@RequestBody NotificationRequest request) {
        notificationClientMapper.sendTweetMentionNotification(request);
        webSocketClient.send(TOPIC_MENTIONS + request.getUserId(), request.getTweet());
    }

    @GetMapping(TWEET_TWEET_ID)
    public void sendTweetNotificationToSubscribers(@PathVariable("tweetId") Long tweetId) {
        notificationClientMapper.sendTweetNotificationToSubscribers(tweetId);
    }

    private void sendTopicNotification(NotificationResponse notification, Long notificationTopicId) {
        if (notification.getId() != null) {
            webSocketClient.send(TOPIC_NOTIFICATIONS + notificationTopicId, notification);
        }
    }
}
