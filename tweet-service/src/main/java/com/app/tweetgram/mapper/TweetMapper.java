package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.request.TweetRequest;
import com.app.tweetgram.dto.response.*;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.enums.NotificationType;
import com.app.tweetgram.enums.ReplyType;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.model.TweetImage;
import com.app.tweetgram.repository.projection.*;
import com.app.tweetgram.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TweetMapper {

    private final BasicMapper basicMapper;
    private final TweetService tweetService;

    public HeaderResponse<TweetResponse> getTweets(Pageable pageable) {
        Page<TweetProjection> tweets = tweetService.getTweets(pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public TweetResponse getTweetById(Long tweetId) {
        TweetProjection tweet = tweetService.getTweetById(tweetId);
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }

    public HeaderResponse<TweetUserResponse> getUserTweets(Long userId, Pageable pageable) {
        Page<TweetUserProjection> tweets = tweetService.getUserTweets(userId, pageable);
        return basicMapper.getHeaderResponse(tweets, TweetUserResponse.class);
    }

    public HeaderResponse<TweetResponse> getUserMediaTweets(Long userId, Pageable pageable) {
        Page<TweetProjection> tweets = tweetService.getUserMediaTweets(userId, pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public List<ProfileTweetImageResponse> getUserTweetImages(Long userId) {
        List<ProfileTweetImageProjection> tweets = tweetService.getUserTweetImages(userId);
        return basicMapper.convertToResponseList(tweets, ProfileTweetImageResponse.class);
    }

    public TweetAdditionalInfoResponse getTweetAdditionalInfoById(Long tweetId) {
        TweetAdditionalInfoProjection additionalInfo = tweetService.getTweetAdditionalInfoById(tweetId);
        return basicMapper.convertToResponse(additionalInfo, TweetAdditionalInfoResponse.class);
    }

    public List<TweetResponse> getRepliesByTweetId(Long tweetId) {
        List<TweetProjection> tweets = tweetService.getRepliesByTweetId(tweetId);
        return basicMapper.convertToResponseList(tweets, TweetResponse.class);
    }

    public HeaderResponse<TweetResponse> getQuotesByTweetId(Pageable pageable, Long tweetId) {
        Page<TweetProjection> tweets = tweetService.getQuotesByTweetId(pageable, tweetId);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public HeaderResponse<TweetResponse> getMediaTweets(Pageable pageable) {
        Page<TweetProjection> tweets = tweetService.getMediaTweets(pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public HeaderResponse<TweetResponse> getTweetsWithVideo(Pageable pageable) {
        Page<TweetProjection> tweets = tweetService.getTweetsWithVideo(pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public HeaderResponse<TweetResponse> getFollowersTweets(Pageable pageable) {
        Page<TweetProjection> tweets = tweetService.getFollowersTweets(pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public TweetImageResponse uploadTweetImage(MultipartFile file) {
        TweetImage tweetImage = tweetService.uploadTweetImage(file);
        return basicMapper.convertToResponse(tweetImage, TweetImageResponse.class);
    }

    public HeaderResponse<UserResponse> getTaggedImageUsers(Long tweetId, Pageable pageable) {
        return tweetService.getTaggedImageUsers(tweetId, pageable);
    }

    public TweetResponse createTweet(TweetRequest tweetRequest) {
        return tweetService.createNewTweet(basicMapper.convertToResponse(tweetRequest, Tweet.class));
    }

    public String deleteTweet(Long tweetId) {
        return tweetService.deleteTweet(tweetId);
    }

    public HeaderResponse<TweetResponse> searchTweets(String text, Pageable pageable) {
        Page<TweetProjection> tweets = tweetService.searchTweets(text, pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public NotificationReplyResponse replyTweet(Long tweetId, TweetRequest tweetRequest) {
        TweetResponse replyTweet = tweetService.replyTweet(tweetId, basicMapper.convertToResponse(tweetRequest, Tweet.class));
        return new NotificationReplyResponse(tweetId, NotificationType.REPLY, replyTweet);
    }

    public TweetResponse quoteTweet(Long tweetId, TweetRequest tweetRequest) {
        return tweetService.quoteTweet(tweetId, basicMapper.convertToResponse(tweetRequest, Tweet.class));
    }

    public TweetResponse changeTweetReplyType(Long tweetId, ReplyType replyType) {
        TweetProjection tweet = tweetService.changeTweetReplyType(tweetId, replyType);
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }
}
