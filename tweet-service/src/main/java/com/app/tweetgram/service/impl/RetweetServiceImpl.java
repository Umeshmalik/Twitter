package com.app.tweetgram.service.impl;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.enums.NotificationType;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.model.Retweet;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.repository.RetweetRepository;
import com.app.tweetgram.repository.TweetRepository;
import com.app.tweetgram.repository.projection.RetweetProjection;
import com.app.tweetgram.repository.projection.TweetUserProjection;
import com.app.tweetgram.service.RetweetService;
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
public class RetweetServiceImpl implements RetweetService {

    private final TweetRepository tweetRepository;
    private final TweetServiceHelper tweetServiceHelper;
    private final TweetValidationHelper tweetValidationHelper;
    private final RetweetRepository retweetRepository;
    private final UserClient userClient;

    @Override
    public Page<TweetUserProjection> getUserRetweetsAndReplies(Long userId, Pageable pageable) {
        tweetValidationHelper.validateUserProfile(userId);
        List<TweetUserProjection> replies = tweetRepository.getRepliesByUserId(userId);
        List<RetweetProjection> retweets = retweetRepository.getRetweetsByUserId(userId);
        List<TweetUserProjection> userTweets = tweetServiceHelper.combineTweetsArrays(replies, retweets);
        return tweetServiceHelper.getPageableTweetProjectionList(pageable, userTweets, replies.size() + retweets.size());
    }

    @Override
    public HeaderResponse<UserResponse> getRetweetedUsersByTweetId(Long tweetId, Pageable pageable) {
        tweetValidationHelper.checkValidTweet(tweetId);
        List<Long> retweetedUserIds = retweetRepository.getRetweetedUserIds(tweetId);
        return userClient.getUsersByIds(new IdsRequest(retweetedUserIds), pageable);
    }

    @Override
    @Transactional
    public NotificationResponse retweet(Long tweetId) {
        Tweet tweet = tweetValidationHelper.checkValidTweet(tweetId);
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        Retweet retweet = retweetRepository.isTweetRetweeted(authUserId, tweetId);
        boolean isRetweeted;

        if (retweet != null) {
            retweetRepository.delete(retweet);
            userClient.updateTweetCount(false);
            isRetweeted = false;
        } else {
            Retweet newRetweet = new Retweet(authUserId, tweetId);
            retweetRepository.save(newRetweet);
            userClient.updateTweetCount(true);
            isRetweeted = true;
        }
        return tweetServiceHelper.sendNotification(NotificationType.RETWEET, isRetweeted, tweet.getAuthorId(), authUserId, tweetId);
    }
}
