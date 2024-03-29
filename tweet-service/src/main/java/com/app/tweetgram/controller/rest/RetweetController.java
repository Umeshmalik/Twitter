package com.app.tweetgram.controller.rest;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.dto.response.notification.NotificationResponse;
import com.app.tweetgram.dto.response.notification.NotificationTweetResponse;
import com.app.tweetgram.dto.response.TweetUserResponse;
import com.app.tweetgram.feign.WebSocketClient;
import com.app.tweetgram.mapper.RetweetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.app.tweetgram.constants.PathConstants.*;
import static com.app.tweetgram.constants.WebsocketConstants.TOPIC_USER_UPDATE_TWEET;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_TWEETS)
public class RetweetController {

    private final RetweetMapper retweetMapper;
    private final WebSocketClient webSocketClient;

    @GetMapping(REPLIES_USER_ID)
    public ResponseEntity<List<TweetUserResponse>> getUserRetweetsAndReplies(@PathVariable("userId") Long userId,
                                                                             @PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<TweetUserResponse> response = retweetMapper.getUserRetweetsAndReplies(userId, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping(TWEET_ID_RETWEETED_USERS)
    public ResponseEntity<List<UserResponse>> getRetweetedUsersByTweetId(@PathVariable("tweetId") Long tweetId,
                                                                         @PageableDefault(size = 15) Pageable pageable) {
        HeaderResponse<UserResponse> response = retweetMapper.getRetweetedUsersByTweetId(tweetId, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping(RETWEET_USER_ID_TWEET_ID)
    public ResponseEntity<NotificationTweetResponse> retweet(@PathVariable("userId") Long userId,
                                                             @PathVariable("tweetId") Long tweetId) {
        NotificationResponse notification = retweetMapper.retweet(tweetId);
        webSocketClient.send(TOPIC_USER_UPDATE_TWEET + userId, notification);
        return ResponseEntity.ok(notification.getTweet());
    }
}
