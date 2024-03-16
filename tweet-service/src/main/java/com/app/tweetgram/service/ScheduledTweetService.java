package com.app.tweetgram.service;

import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.repository.projection.TweetProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduledTweetService {

    Page<TweetProjection> getScheduledTweets(Pageable pageable);

    TweetProjection createScheduledTweet(Tweet tweet);

    TweetProjection updateScheduledTweet(Tweet tweet);

    String deleteScheduledTweets(List<Long> tweetsIds);
}
