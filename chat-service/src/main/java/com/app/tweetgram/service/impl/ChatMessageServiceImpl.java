package com.app.tweetgram.service.impl;

import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.exception.ApiRequestException;
import com.app.tweetgram.feign.TweetClient;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.model.Chat;
import com.app.tweetgram.model.ChatMessage;
import com.app.tweetgram.model.ChatParticipant;
import com.app.tweetgram.repository.ChatMessageRepository;
import com.app.tweetgram.repository.ChatParticipantRepository;
import com.app.tweetgram.repository.ChatRepository;
import com.app.tweetgram.repository.projection.ChatMessageProjection;
import com.app.tweetgram.repository.projection.ChatProjection;
import com.app.tweetgram.service.ChatMessageService;
import com.app.tweetgram.service.util.ChatServiceHelper;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.tweetgram.constants.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatRepository chatRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatServiceHelper chatServiceHelper;
    private final UserClient userClient;
    private final TweetClient tweetClient;

    @Override
    public List<ChatMessageProjection> getChatMessages(Long chatId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        chatRepository.getChatById(chatId, authUserId, ChatProjection.class)
                .orElseThrow(() -> new ApiRequestException(CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
        return chatMessageRepository.getChatMessages(chatId);
    }

    @Override
    @Transactional
    public Long readChatMessages(Long chatId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        chatRepository.getChatById(chatId, authUserId, ChatProjection.class)
                .orElseThrow(() -> new ApiRequestException(CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
        chatMessageRepository.readChatMessages(chatId, authUserId);
        return chatMessageRepository.getUnreadMessagesCount(authUserId);
    }

    @Override
    @Transactional
    public Map<Long, ChatMessageProjection> addMessage(ChatMessage chatMessage, Long chatId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        Chat chat = chatRepository.getChatById(chatId, authUserId, Chat.class)
                .orElseThrow(() -> new ApiRequestException(CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
        Long chatParticipantId = chatParticipantRepository.getChatParticipantId(authUserId, chatId);
        chatServiceHelper.isParticipantBlocked(authUserId, chatParticipantId);
        chatMessage.setAuthorId(authUserId);
        chatMessage.setChat(chat);
        chatMessageRepository.save(chatMessage);
        chatParticipantRepository.updateParticipantWhoLeftChat(chatParticipantId, chatId);
        chat.getMessages().add(chatMessage);
        ChatMessageProjection message = chatMessageRepository.getChatMessageById(chatMessage.getId()).get();
        Map<Long, ChatMessageProjection> chatParticipants = new HashMap<>();
        chatParticipantRepository.getChatParticipantIds(chatId)
                .forEach(userId -> chatParticipants.put(userId, message));
        return chatParticipants;
    }

    @Override
    @Transactional
    public Map<Long, ChatMessageProjection> addMessageWithTweet(String text, Long tweetId, List<Long> usersIds) {
        if (!tweetClient.isTweetExists(tweetId)) {
            throw new ApiRequestException(TWEET_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        List<Long> validUserIds = userClient.validateChatUsersIds(new IdsRequest(usersIds));
        Map<Long, ChatMessageProjection> chatParticipants = new HashMap<>();
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        validUserIds.forEach(userId -> {
            ChatMessage chatMessage = new ChatMessage(text, tweetId, authUserId);
            Chat chat = chatRepository.getChatByParticipants(authUserId, userId);
            Boolean isUserBlockedByMyProfile = userClient.isMyProfileBlockedByUser(userId);

            if (chat == null && !isUserBlockedByMyProfile) {
                Chat newChat = new Chat();
                chatRepository.save(newChat);
                ChatParticipant authorParticipant = chatParticipantRepository.save(new ChatParticipant(authUserId, newChat));
                ChatParticipant userParticipant = chatParticipantRepository.save(new ChatParticipant(userId, newChat));
                newChat.setParticipants(List.of(authorParticipant, userParticipant));
                chatMessage.setChat(newChat);
                chatMessageRepository.save(chatMessage);
            } else if (!isUserBlockedByMyProfile) {
                chatMessage.setChat(chat);
                chatMessageRepository.save(chatMessage);
                chatParticipantRepository.updateParticipantWhoLeftChat(userId, chat.getId());
                chat.getMessages().add(chatMessage);
            }
            ChatMessageProjection message = chatMessageRepository.getChatMessageById(chatMessage.getId()).get();
            chatParticipants.put(userId, message);
        });
        return chatParticipants;
    }
}
