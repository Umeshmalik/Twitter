package com.app.tweetgram.service.impl;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.enums.NotificationType;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.model.LikeTweet;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.repository.LikeTweetRepository;
import com.app.tweetgram.repository.projection.LikeTweetProjection;
import com.app.tweetgram.service.LikeTweetService;
import com.app.tweetgram.service.util.TweetValidationHelper;
import com.app.tweetgram.util.AuthUtil;
import com.app.tweetgram.service.util.TweetServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeTweetServiceImpl implements LikeTweetService {

    private final LikeTweetRepository likeTweetRepository;
    private final TweetServiceHelper tweetServiceHelper;
    private final TweetValidationHelper tweetValidationHelper;
    private final UserClient userClient;

    @Override
    public Page<LikeTweetProjection> getUserLikedTweets(Long userId, Pageable pageable) {
        tweetValidationHelper.validateUserProfile(userId);
        return likeTweetRepository.getUserLikedTweets(userId, pageable);
    }

    @Override
    public HeaderResponse<UserResponse> getLikedUsersByTweetId(Long tweetId, Pageable pageable) {
        tweetValidationHelper.checkValidTweet(tweetId);
        List<Long> likedUserIds = likeTweetRepository.getLikedUserIds(tweetId);
        return userClient.getUsersByIds(new IdsRequest(likedUserIds), pageable);
    }

    @Override
    @Transactional
    public NotificationResponse likeTweet(Long tweetId) {
        Tweet tweet = tweetValidationHelper.checkValidTweet(tweetId);
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        LikeTweet likedTweet = likeTweetRepository.getLikedTweet(authUserId, tweetId);
        boolean isTweetLiked;

        if (likedTweet != null) {
            likeTweetRepository.delete(likedTweet);
            userClient.updateLikeCount(false);
            isTweetLiked = false;
        } else {
            LikeTweet newLikeTweet = new LikeTweet(authUserId, tweetId);
            likeTweetRepository.save(newLikeTweet);
            userClient.updateLikeCount(true);
            isTweetLiked = true;
        }
        return tweetServiceHelper.sendNotification(NotificationType.LIKE, isTweetLiked, tweet.getAuthorId(), authUserId, tweetId);
    }
}
