package com.app.tweetgram.controller;

import com.app.tweetgram.dto.request.ChatMessageRequest;
import com.app.tweetgram.dto.request.MessageWithTweetRequest;
import com.app.tweetgram.dto.response.ChatMessageResponse;
import com.app.tweetgram.feign.WebSocketClient;
import com.app.tweetgram.mapper.ChatMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.tweetgram.constants.PathConstants.*;
import static com.app.tweetgram.constants.WebsocketConstants.TOPIC_CHAT;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_CHAT)
public class ChatMessageController {

    private final ChatMessageMapper chatMessageMapper;
    private final WebSocketClient webSocketClient;

    @GetMapping(CHAT_ID_MESSAGES)
    public ResponseEntity<List<ChatMessageResponse>> getChatMessages(@PathVariable("chatId") Long chatId) {
        return ResponseEntity.ok(chatMessageMapper.getChatMessages(chatId));
    }

    @GetMapping(CHAT_ID_READ_MESSAGES)
    public ResponseEntity<Long> readChatMessages(@PathVariable("chatId") Long chatId) {
        return ResponseEntity.ok(chatMessageMapper.readChatMessages(chatId));
    }

    @PostMapping(ADD_MESSAGE)
    public ResponseEntity<Void> addMessage(@RequestBody ChatMessageRequest request) {
        chatMessageMapper.addMessage(request)
                .forEach((userId, message) -> {
                    webSocketClient.send(TOPIC_CHAT + userId, message);
                }
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping(ADD_MESSAGE_TWEET)
    public ResponseEntity<Void> addMessageWithTweet(@RequestBody MessageWithTweetRequest request) {
        chatMessageMapper.addMessageWithTweet(request)
                .forEach((userId, message) -> webSocketClient.send(TOPIC_CHAT + userId, message));
        return ResponseEntity.ok().build();
    }
}
