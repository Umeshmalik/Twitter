package com.app.tweetgram.service;

import com.app.tweetgram.model.ChatMessage;
import com.app.tweetgram.repository.projection.ChatMessageProjection;

import java.util.List;
import java.util.Map;

public interface ChatMessageService {

    List<ChatMessageProjection> getChatMessages(Long chatId);

    Long readChatMessages(Long chatId);

    Map<Long, ChatMessageProjection> addMessage(ChatMessage chatMessage, Long chatId);

    Map<Long, ChatMessageProjection> addMessageWithTweet(String text, Long tweetId, List<Long> usersIds);
}
