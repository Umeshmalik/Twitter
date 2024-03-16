package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.app.tweetgram.constants.FeignConstants.TWEET_SERVICE;
import static com.app.tweetgram.constants.PathConstants.API_V1_TWEETS;
import static com.app.tweetgram.constants.PathConstants.IDS;

@CircuitBreaker(name = TWEET_SERVICE, fallbackMethod = "defaultEmptyTweetList")
@FeignClient(value = TWEET_SERVICE, path = API_V1_TWEETS, configuration = FeignConfiguration.class)
public interface TweetClient {

    @PostMapping(IDS)
    List<TweetResponse> getTweetsByIds(@RequestBody IdsRequest request);

    default ArrayList<TweetResponse> defaultEmptyTweetList(Throwable throwable) {
        return new ArrayList<>();
    }
}
