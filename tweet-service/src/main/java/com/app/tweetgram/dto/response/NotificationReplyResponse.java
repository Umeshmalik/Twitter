package com.app.tweetgram.dto.response;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationReplyResponse {
    private Long tweetId;
    private NotificationType notificationType;
    private TweetResponse tweet;
}
