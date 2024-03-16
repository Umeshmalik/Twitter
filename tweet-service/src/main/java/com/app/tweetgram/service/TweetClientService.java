package com.app.tweetgram.service;

import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.repository.projection.ChatTweetProjection;
import com.app.tweetgram.repository.projection.NotificationTweetProjection;
import com.app.tweetgram.repository.projection.TweetProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TweetClientService {

    List<TweetProjection> getTweetsByIds(IdsRequest requests);

    Page<TweetProjection> getTweetsByUserIds(IdsRequest request, Pageable pageable);

    TweetProjection getTweetById(Long tweetId);

    Page<TweetProjection> getTweetsByIds(IdsRequest request, Pageable pageable);

    NotificationTweetProjection getNotificationTweet(Long tweetId);

    Boolean isTweetExists(Long tweetId);

    Long getTweetCountByText(String text);

    ChatTweetProjection getChatTweet(Long tweetId);
}
