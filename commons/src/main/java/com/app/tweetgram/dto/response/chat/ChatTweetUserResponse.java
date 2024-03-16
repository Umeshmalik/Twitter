package com.app.tweetgram.dto.response.chat;

import lombok.Data;

@Data
public class ChatTweetUserResponse {
    private Long id;
    private String fullName;
    private String username;
    private String avatar;
}
