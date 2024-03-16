package com.app.tweetgram.service;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.repository.projection.LikeTweetProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeTweetService {

    Page<LikeTweetProjection> getUserLikedTweets(Long userId, Pageable pageable);

    HeaderResponse<UserResponse> getLikedUsersByTweetId(Long tweetId, Pageable pageable);

    NotificationResponse likeTweet(Long tweetId);
}
