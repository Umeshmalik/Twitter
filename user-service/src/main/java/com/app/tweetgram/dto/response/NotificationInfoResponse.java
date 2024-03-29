package com.app.tweetgram.dto.response;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.enums.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationInfoResponse {
    private Long id;
    private LocalDateTime date;
    private NotificationType notificationType;
    private UserResponse user;
    private TweetResponse tweet;
}
