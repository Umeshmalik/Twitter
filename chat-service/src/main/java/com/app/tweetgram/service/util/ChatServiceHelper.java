package com.app.tweetgram.service.util;

import com.app.tweetgram.dto.response.chat.ChatTweetResponse;
import com.app.tweetgram.dto.response.chat.ChatUserParticipantResponse;
import com.app.tweetgram.exception.ApiRequestException;
import com.app.tweetgram.feign.TweetClient;
import com.app.tweetgram.feign.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.app.tweetgram.constants.ErrorMessage.CHAT_PARTICIPANT_BLOCKED;

@Component
@RequiredArgsConstructor
public class ChatServiceHelper {

    private final UserClient userClient;
    private final TweetClient tweetClient;

    public ChatUserParticipantResponse getChatParticipant(Long userId) {
        return userClient.getChatParticipant(userId);
    }

    public ChatTweetResponse getChatTweet(Long tweetId) {
        return tweetClient.getChatTweet(tweetId);
    }

    public void isParticipantBlocked(Long authUserId, Long userId) {
        Boolean isUserBlockedByMyProfile = userClient.isUserBlockedByMyProfile(authUserId);
        Boolean isMyProfileBlockedByUser = userClient.isMyProfileBlockedByUser(userId);

        if (isUserBlockedByMyProfile || isMyProfileBlockedByUser) {
            throw new ApiRequestException(CHAT_PARTICIPANT_BLOCKED, HttpStatus.BAD_REQUEST);
        }
    }
}
