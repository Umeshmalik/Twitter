package com.app.tweetgram.service.cron;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.feign.WebSocketClient;
import com.app.tweetgram.model.Tweet;
import com.app.tweetgram.repository.TweetRepository;
import com.app.tweetgram.service.util.TweetServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.tweetgram.constants.WebsocketConstants.TOPIC_FEED_SCHEDULE;

@Service
@Transactional
@RequiredArgsConstructor
public class CronService {

    private final WebSocketClient webSocketClient;
    private final TweetRepository tweetRepository;
    private final TweetServiceHelper tweetServiceHelper;
    private final UserClient userClient;

    @Scheduled(initialDelay = 30000, fixedDelay = 30000)
    public void sendTweetBySchedule() {
        List<Tweet> tweets = tweetRepository.findAllByScheduledDate(LocalDateTime.now());
        tweets.forEach((tweet) -> {
            if (tweet.getText().contains("youtube.com") || !tweet.getImages().isEmpty()) {
                userClient.updateMediaTweetCount(true);
            } else {
                userClient.updateTweetCount(true);
            }
            tweet.setScheduledDate(null);
            tweet.setDateTime(LocalDateTime.now());
            TweetResponse tweetResponse = tweetServiceHelper.processTweetResponse(tweet);
            webSocketClient.send(TOPIC_FEED_SCHEDULE, tweetResponse);
        });
    }
}
