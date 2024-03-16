package com.app.tweetgram.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.tweetgram.dto.response.tweet.TweetAdditionalInfoUserResponse;
import com.app.tweetgram.enums.ReplyType;
import lombok.Data;

@Data
public class TweetAdditionalInfoResponse {
    private String text;
    private ReplyType replyType;
    private Long addressedTweetId;
    private TweetAdditionalInfoUserResponse user;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
