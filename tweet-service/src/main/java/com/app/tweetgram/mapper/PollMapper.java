package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.request.TweetRequest;
import com.app.tweetgram.dto.request.VoteRequest;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.repository.projection.TweetProjection;
import com.app.tweetgram.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PollMapper {

    private final BasicMapper basicMapper;
    private final PollService pollService;

    public TweetResponse createPoll(TweetRequest tweetRequest) {
        Tweet tweet = basicMapper.convertToResponse(tweetRequest, Tweet.class);
        return pollService.createPoll(tweetRequest.getPollDateTime(), tweetRequest.getChoices(), tweet);
    }

    public TweetResponse voteInPoll(VoteRequest voteRequest) {
        TweetProjection tweet = pollService.voteInPoll(voteRequest.getTweetId(), voteRequest.getPollId(),
                voteRequest.getPollChoiceId());
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }
}
