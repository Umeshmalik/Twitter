package com.app.tweetgram.service;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.repository.projection.TweetProjection;

import java.util.List;

public interface PollService {

    TweetResponse createPoll(Long pollDateTime, List<String> choices, Tweet tweet);

    TweetProjection voteInPoll(Long tweetId, Long pollId, Long pollChoiceId);
}
