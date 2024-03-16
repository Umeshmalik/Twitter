package com.app.tweetgram.repository.projection;

import com.app.tweetgram.dto.response.chat.ChatUserParticipantResponse;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatProjection {
    Long getId();
    LocalDateTime getCreationDate();
    List<ChatParticipantProjection> getParticipants();

    interface ChatParticipantProjection {
        Long getId();
        Long getUserId();

        @Value("#{@chatServiceHelper.getChatParticipant(target.userId)}")
        ChatUserParticipantResponse getUser();
        boolean getLeftChat();
    }
}
