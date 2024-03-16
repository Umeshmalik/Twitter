package com.app.tweetgram.repository.projection;

public interface ChatParticipantProjection {
    Long getId();
    boolean getLeftChat();
    ChatProjection getChat();
    ChatUserProjection getUser();

    interface ChatUserProjection {
        Long getId();
    }
}
