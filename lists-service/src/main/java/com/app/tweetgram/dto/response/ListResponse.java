package com.app.tweetgram.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.tweetgram.dto.response.user.CommonUserResponse;
import lombok.Data;

@Data
public class ListResponse {
    private Long id;
    private String name;
    private String description;
    private String altWallpaper;
    private String wallpaper;
    private CommonUserResponse listOwner;

    @JsonProperty("isFollower")
    private boolean isFollower;

    @JsonProperty("isListPinned")
    private boolean isListPinned;
}
