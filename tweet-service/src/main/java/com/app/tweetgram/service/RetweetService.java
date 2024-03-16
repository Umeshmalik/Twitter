package com.app.tweetgram.service;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.repository.projection.TweetUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RetweetService {

    Page<TweetUserProjection> getUserRetweetsAndReplies(Long userId, Pageable pageable);

    HeaderResponse<UserResponse> getRetweetedUsersByTweetId(Long tweetId, Pageable pageable);

    NotificationResponse retweet(Long tweetId);
}
