package com.app.tweetgram.dto.response;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TweetUserResponse extends TweetResponse {
    private List<Long> retweetsUserIds;
}
