package com.app.tweetgram.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.tweetgram.dto.response.chat.ChatUserParticipantResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatResponse {
    private Long id;
    private LocalDateTime creationDate;
    private List<ParticipantResponse> participants;

    @Data
    static class ParticipantResponse {
        private Long id;
        private ChatUserParticipantResponse user;

        @JsonProperty("isLeftChat")
        private boolean leftChat;
    }
}
