package com.app.tweetgram.repository.projection;

public interface UserPrincipalProjection {
    Long getId();
    String getEmail();
    String getActivationCode();
}
