package com.app.tweetgram.service.impl;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.UserChatResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.exception.ApiRequestException;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.model.Chat;
import com.app.tweetgram.model.ChatParticipant;
import com.app.tweetgram.repository.ChatMessageRepository;
import com.app.tweetgram.repository.ChatParticipantRepository;
import com.app.tweetgram.repository.ChatRepository;
import com.app.tweetgram.service.ChatParticipantService;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.tweetgram.constants.ErrorMessage.CHAT_NOT_FOUND;
import static com.app.tweetgram.constants.ErrorMessage.CHAT_PARTICIPANT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ChatParticipantServiceImpl implements ChatParticipantService {

    private final ChatRepository chatRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserClient userClient;

    @Override
    public UserResponse getParticipant(Long participantId, Long chatId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        boolean isChatExists = chatRepository.isChatExists(chatId, authUserId);

        if (!isChatExists) {
            throw new ApiRequestException(CHAT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        Long userId = chatParticipantRepository.getParticipantUserId(participantId, chatId)
                .orElseThrow(() -> new ApiRequestException(CHAT_PARTICIPANT_NOT_FOUND, HttpStatus.NOT_FOUND));
        return userClient.getUserResponseById(userId);
    }

    @Override
    @Transactional
    public String leaveFromConversation(Long participantId, Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ApiRequestException(CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
        int isChatParticipantUpdated = chatParticipantRepository.leaveFromConversation(participantId, chatId);

        if (isChatParticipantUpdated != 1) {
            throw new ApiRequestException(CHAT_PARTICIPANT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        boolean isParticipantsLeftFromChat = chat.getParticipants().stream().allMatch(ChatParticipant::isLeftChat);

        if (isParticipantsLeftFromChat) {
            chatMessageRepository.deleteAll(chat.getMessages());
            chatParticipantRepository.deleteAll(chat.getParticipants());
            chatRepository.delete(chat);
            return "Chat successfully deleted";
        }
        return "Successfully left the chat";
    }

    @Override
    public HeaderResponse<UserChatResponse> searchUsersByUsername(String username, Pageable pageable) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        HeaderResponse<UserChatResponse> users = userClient.searchUsersByUsername(username, pageable);
        List<UserChatResponse> usersResponse = users.getItems().stream()
                .peek(user -> {
                    Chat chat = chatRepository.getChatByParticipants(authUserId, user.getId());

                    if (chat != null) {
                        user.setUserChatParticipant(true);
                    }
                }).toList();
        users.setItems(usersResponse);
        return users;
    }
}
