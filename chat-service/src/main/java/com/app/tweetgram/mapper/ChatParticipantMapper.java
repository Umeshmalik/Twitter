package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.UserChatResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.service.ChatParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatParticipantMapper {

    private final ChatParticipantService chatParticipantService;

    public UserResponse getParticipant(Long participantId, Long chatId) {
        return chatParticipantService.getParticipant(participantId, chatId);
    }

    public String leaveFromConversation(Long participantId, Long chatId) {
        return chatParticipantService.leaveFromConversation(participantId, chatId);
    }

    public HeaderResponse<UserChatResponse> searchParticipantsByUsername(String username, Pageable pageable) {
        return chatParticipantService.searchUsersByUsername(username, pageable);
    }
}
