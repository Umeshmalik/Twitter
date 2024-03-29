package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.request.ChatMessageRequest;
import com.app.tweetgram.dto.request.MessageWithTweetRequest;
import com.app.tweetgram.dto.response.ChatMessageResponse;
import com.app.tweetgram.model.ChatMessage;
import com.app.tweetgram.repository.projection.ChatMessageProjection;
import com.app.tweetgram.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ChatMessageMapper {

    private final BasicMapper basicMapper;
    private final ChatMessageService chatMessageService;

    public List<ChatMessageResponse> getChatMessages(Long chatId) {
        List<ChatMessageProjection> chatMessages = chatMessageService.getChatMessages(chatId);
        return basicMapper.convertToResponseList(chatMessages, ChatMessageResponse.class);
    }

    public Long readChatMessages(Long chatId) {
        return chatMessageService.readChatMessages(chatId);
    }

    public Map<Long, ChatMessageResponse> addMessage(ChatMessageRequest request) {
        Map<Long, ChatMessageProjection> messages = chatMessageService.addMessage(
                basicMapper.convertToResponse(request, ChatMessage.class), request.getChatId());
        return getChatMessageResponse(messages);
    }

    public Map<Long, ChatMessageResponse> addMessageWithTweet(MessageWithTweetRequest request) {
        Map<Long, ChatMessageProjection> messages = chatMessageService.addMessageWithTweet(
                request.getText(), request.getTweetId(), request.getUsersIds());
        return getChatMessageResponse(messages);
    }

    private Map<Long, ChatMessageResponse> getChatMessageResponse(Map<Long, ChatMessageProjection> messages) {
        Map<Long, ChatMessageResponse> messagesResponse = new HashMap<>();
        messages.forEach((userId, messageProjection) -> {
            ChatMessageResponse messageResponse = basicMapper.convertToResponse(messageProjection, ChatMessageResponse.class);
            messagesResponse.put(userId, messageResponse);
        });
        return messagesResponse;
    }
}
