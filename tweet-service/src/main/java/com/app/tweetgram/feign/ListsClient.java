package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import com.app.tweetgram.dto.response.tweet.TweetListResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.app.tweetgram.constants.FeignConstants.LISTS_SERVICE;
import static com.app.tweetgram.constants.PathConstants.API_V1_LISTS;
import static com.app.tweetgram.constants.PathConstants.TWEET_LIST_ID;

@FeignClient(value = LISTS_SERVICE, path = API_V1_LISTS, configuration = FeignConfiguration.class)
public interface ListsClient {

    @CircuitBreaker(name = LISTS_SERVICE, fallbackMethod = "defaultEmptyTweetList")
    @GetMapping(TWEET_LIST_ID)
        TweetListResponse getTweetList(@PathVariable("listId") Long listId);

    default TweetListResponse defaultEmptyTweetList(Throwable throwable) {
        return new TweetListResponse();
    }
}
