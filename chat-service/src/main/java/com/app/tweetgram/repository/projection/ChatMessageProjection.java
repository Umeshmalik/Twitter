package com.app.tweetgram.repository.projection;

import com.app.tweetgram.dto.response.chat.ChatTweetResponse;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface ChatMessageProjection {
    Long getId();
    String getText();
    LocalDateTime getDate();
    Long getAuthorId();
    Long getTweetId();
    @Value("#{target.tweetId == null ? null : @chatServiceHelper.getChatTweet(target.tweetId)}")
    ChatTweetResponse getTweet();
    ChatProjection getChat();

    interface ChatProjection {
        Long getId();
    }
}
