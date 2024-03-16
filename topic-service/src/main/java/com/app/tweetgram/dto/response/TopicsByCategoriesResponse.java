package com.app.tweetgram.dto.response;

import com.app.tweetgram.enums.TopicCategory;
import com.app.tweetgram.repository.projection.TopicProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicsByCategoriesResponse {
    private TopicCategory topicCategory;
    private List<TopicProjection> topicsByCategories;
}
