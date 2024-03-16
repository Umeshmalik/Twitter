package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import com.app.tweetgram.dto.request.NotificationRequest;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.app.tweetgram.constants.FeignConstants.NOTIFICATION_SERVICE;
import static com.app.tweetgram.constants.PathConstants.*;

@CircuitBreaker(name = NOTIFICATION_SERVICE)
@FeignClient(name = NOTIFICATION_SERVICE, path = API_V1_NOTIFICATION, configuration = FeignConfiguration.class)
public interface NotificationClient {

    @PostMapping(TWEET)
    NotificationResponse sendTweetNotification(@RequestBody NotificationRequest request);

    @PostMapping(MENTION)
    void sendTweetMentionNotification(@RequestBody NotificationRequest request);

    @GetMapping(TWEET_TWEET_ID)
    void sendTweetNotificationToSubscribers(@PathVariable("tweetId") Long tweetId);
}
