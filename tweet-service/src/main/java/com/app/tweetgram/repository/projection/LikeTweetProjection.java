package com.app.tweetgram.repository.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface LikeTweetProjection {
    Long getId();
    LocalDateTime getLikeTweetDate();
    Long getTweetId();

    @Value("#{@tweetProjectionHelper.getTweetProjection(target.tweetId)}")
    TweetProjection getTweet();
}
