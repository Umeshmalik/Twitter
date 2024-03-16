package com.app.tweetgram.service;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.UserChatResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import org.springframework.data.domain.Pageable;

public interface ChatParticipantService {

    UserResponse getParticipant(Long participantId, Long chatId);

    String leaveFromConversation(Long participantId, Long chatId);

    HeaderResponse<UserChatResponse> searchUsersByUsername(String username, Pageable pageable);
}
