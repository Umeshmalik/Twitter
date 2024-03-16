package com.app.tweetgram.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.tweetgram.enums.TopicCategory;
import lombok.Data;

@Data
public class TopicResponse {
    private Long id;
    private String topicName;
    private TopicCategory topicCategory;

    @JsonProperty("isTopicFollowed")
    private boolean isTopicFollowed;

    @JsonProperty("isTopicNotInterested")
    private boolean isTopicNotInterested;
}
