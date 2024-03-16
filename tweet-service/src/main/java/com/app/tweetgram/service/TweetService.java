package com.app.tweetgram.service;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.enums.ReplyType;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.model.TweetImage;
import com.app.tweetgram.repository.projection.ProfileTweetImageProjection;
import com.app.tweetgram.repository.projection.TweetAdditionalInfoProjection;
import com.app.tweetgram.repository.projection.TweetProjection;
import com.app.tweetgram.repository.projection.TweetUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TweetService {

    Page<TweetProjection> getTweets(Pageable pageable);

    TweetProjection getTweetById(Long tweetId);

    Page<TweetUserProjection> getUserTweets(Long userId, Pageable pageable);

    Page<TweetProjection> getUserMediaTweets(Long userId, Pageable pageable);

    List<ProfileTweetImageProjection> getUserTweetImages(Long userId);

    TweetAdditionalInfoProjection getTweetAdditionalInfoById(Long tweetId);

    List<TweetProjection> getRepliesByTweetId(Long tweetId);

    Page<TweetProjection> getQuotesByTweetId(Pageable pageable, Long tweetId);

    Page<TweetProjection> getMediaTweets(Pageable pageable);

    Page<TweetProjection> getTweetsWithVideo(Pageable pageable);

    Page<TweetProjection> getFollowersTweets(Pageable pageable);

    TweetImage uploadTweetImage(MultipartFile file);

    HeaderResponse<UserResponse> getTaggedImageUsers(Long tweetId, Pageable pageable);

    TweetResponse createNewTweet(Tweet tweet);

    String deleteTweet(Long tweetId);

    Page<TweetProjection> searchTweets(String text, Pageable pageable);

    TweetResponse replyTweet(Long tweetId, Tweet reply);

    TweetResponse quoteTweet(Long tweetId, Tweet quote);

    TweetProjection changeTweetReplyType(Long tweetId, ReplyType replyType);
}
