package com.app.tweetgram.repository.projection;

import com.app.tweetgram.dto.response.tweet.TweetAdditionalInfoUserResponse;
import com.app.tweetgram.enums.ReplyType;
import org.springframework.beans.factory.annotation.Value;

public interface TweetAdditionalInfoProjection {
    String getText();
    ReplyType getReplyType();
    Long getAddressedTweetId();
    boolean isDeleted();
    Long getAuthorId();

    @Value("#{@tweetProjectionHelper.getTweetAdditionalInfoUser(target.authorId)}")
    TweetAdditionalInfoUserResponse getUser();
}
