package com.app.tweetgram.repository.projection;

import com.app.tweetgram.enums.TopicCategory;
import org.springframework.beans.factory.annotation.Value;

public interface NotInterestedTopicProjection {
    Long getId();
    String getTopicName();
    TopicCategory getTopicCategory();

    @Value("#{true}")
    boolean getIsTopicNotInterested();
}
