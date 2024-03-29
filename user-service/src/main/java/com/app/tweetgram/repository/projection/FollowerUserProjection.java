package com.app.tweetgram.repository.projection;

public interface FollowerUserProjection {
    Long getId();
    String getFullName();
    String getUsername();
    String getAbout();
    String getAvatar();
}
