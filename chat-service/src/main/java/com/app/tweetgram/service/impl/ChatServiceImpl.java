package com.app.tweetgram.service.impl;

import com.app.tweetgram.exception.ApiRequestException;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.model.Chat;
import com.app.tweetgram.model.ChatParticipant;
import com.app.tweetgram.repository.ChatParticipantRepository;
import com.app.tweetgram.repository.ChatRepository;
import com.app.tweetgram.repository.projection.ChatProjection;
import com.app.tweetgram.service.ChatService;
import com.app.tweetgram.service.util.ChatServiceHelper;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.app.tweetgram.constants.ErrorMessage.CHAT_NOT_FOUND;
import static com.app.tweetgram.constants.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatServiceHelper chatServiceHelper;
    private final UserClient userClient;

    @Override
    public ChatProjection getChatById(Long chatId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return chatRepository.getChatById(chatId, authUserId, ChatProjection.class)
                .orElseThrow(() -> new ApiRequestException(CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ChatProjection> getUserChats() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return chatRepository.getChatsByUserId(authUserId);
    }

    @Override
    @Transactional
    public ChatProjection createChat(Long userId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        Boolean isUserExists = userClient.isUserExists(userId);

        if (!isUserExists) {
            throw new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        chatServiceHelper.isParticipantBlocked(authUserId, userId);
        Chat chat = chatRepository.getChatByParticipants(authUserId, userId);

        if (chat == null) {
            Chat newChat = new Chat();
            chatRepository.save(newChat);
            ChatParticipant authUserParticipant = chatParticipantRepository.save(new ChatParticipant(authUserId, newChat));
            ChatParticipant userParticipant = chatParticipantRepository.save(new ChatParticipant(userId, newChat));
            newChat.setParticipants(Arrays.asList(authUserParticipant, userParticipant));
            return chatRepository.getChatById(newChat.getId());
        }
        return chatRepository.getChatById(chat.getId());
    }
}
