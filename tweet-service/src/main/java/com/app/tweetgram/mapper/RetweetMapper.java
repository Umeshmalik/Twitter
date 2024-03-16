package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.dto.response.TweetUserResponse;
import com.app.tweetgram.repository.projection.TweetUserProjection;
import com.app.tweetgram.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetMapper {

    private final BasicMapper basicMapper;
    private final RetweetService retweetService;

    public HeaderResponse<TweetUserResponse> getUserRetweetsAndReplies(Long userId, Pageable pageable) {
        Page<TweetUserProjection> tweets = retweetService.getUserRetweetsAndReplies(userId, pageable);
        return basicMapper.getHeaderResponse(tweets, TweetUserResponse.class);
    }

    public HeaderResponse<UserResponse> getRetweetedUsersByTweetId(Long tweetId, Pageable pageable) {
        return retweetService.getRetweetedUsersByTweetId(tweetId, pageable);
    }

    public NotificationResponse retweet(Long tweetId) {
        return retweetService.retweet(tweetId);
    }
}
