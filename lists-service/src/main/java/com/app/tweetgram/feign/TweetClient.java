package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.request.IdsRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.app.tweetgram.constants.PathConstants.API_V1_TWEETS;
import static com.app.tweetgram.constants.FeignConstants.TWEET_SERVICE;
import static com.app.tweetgram.constants.PathConstants.USER_IDS;

@CircuitBreaker(name = TWEET_SERVICE)
@FeignClient(value = TWEET_SERVICE, path = API_V1_TWEETS, configuration = FeignConfiguration.class)
public interface TweetClient {

    @PostMapping(USER_IDS)
    HeaderResponse<TweetResponse> getTweetsByUserIds(@RequestBody IdsRequest request,
                                                     @SpringQueryMap Pageable pageable);
}
