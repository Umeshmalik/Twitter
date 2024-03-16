package com.app.tweetgram.repository.projection;

import org.springframework.beans.factory.annotation.Value;

public interface PinnedListProjection {
    Long getId();
    String getName();
    String getAltWallpaper();
    String getWallpaper();
    boolean getIsPrivate();

    @Value("#{@listsServiceHelper.isListPinned(target.id)}")
    boolean getIsListPinned();
}
