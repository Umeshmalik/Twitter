package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import com.app.tweetgram.dto.response.notification.NotificationListResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.app.tweetgram.constants.FeignConstants.LISTS_SERVICE;
import static com.app.tweetgram.constants.PathConstants.API_V1_LISTS;
import static com.app.tweetgram.constants.PathConstants.LIST_ID;

@CircuitBreaker(name = LISTS_SERVICE)
@FeignClient(name = LISTS_SERVICE, path = API_V1_LISTS, configuration = FeignConfiguration.class)
public interface ListsClient {

    @GetMapping(LIST_ID)
    NotificationListResponse getNotificationList(@PathVariable("listId") Long listId);
}
