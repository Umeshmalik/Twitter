package com.app.tweetgram.controller.api;

import com.app.tweetgram.dto.response.tweet.TweetListResponse;
import com.app.tweetgram.dto.response.notification.NotificationListResponse;
import com.app.tweetgram.service.ListsClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_LISTS)
public class ListsApiController {

    private final ListsClientService listsClientService;

    @GetMapping(LIST_ID)
    public NotificationListResponse getNotificationList(@PathVariable("listId") Long listId) {
        return listsClientService.getNotificationList(listId);
    }

    @GetMapping(TWEET_LIST_ID)
    public TweetListResponse getTweetList(@PathVariable("listId") Long listId) {
        return listsClientService.getTweetList(listId);
    }
}
