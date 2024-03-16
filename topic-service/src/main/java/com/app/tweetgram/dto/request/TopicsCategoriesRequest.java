package com.app.tweetgram.dto.request;

import com.app.tweetgram.enums.TopicCategory;
import lombok.Data;

import java.util.List;

@Data
public class TopicsCategoriesRequest {
    private List<TopicCategory> categories;
}
