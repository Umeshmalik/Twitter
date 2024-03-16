package com.app.tweetgram.service;

import com.app.tweetgram.dto.response.TopicsByCategoriesResponse;
import com.app.tweetgram.enums.TopicCategory;
import com.app.tweetgram.repository.projection.FollowedTopicProjection;
import com.app.tweetgram.repository.projection.NotInterestedTopicProjection;
import com.app.tweetgram.repository.projection.TopicProjection;

import java.util.List;

public interface TopicService {

    List<TopicProjection> getTopicsByIds(List<Long> topicsIds);

    List<TopicsByCategoriesResponse> getTopicsByCategories(List<TopicCategory> categories);

    List<FollowedTopicProjection> getFollowedTopics();

    List<TopicProjection> getFollowedTopicsByUserId(Long userId);

    List<NotInterestedTopicProjection> getNotInterestedTopics();

    Boolean processNotInterestedTopic(Long topicId);

    Boolean processFollowTopic(Long topicId);
}
