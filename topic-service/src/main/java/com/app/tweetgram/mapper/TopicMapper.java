package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.response.TopicResponse;
import com.app.tweetgram.dto.response.TopicsByCategoriesResponse;
import com.app.tweetgram.enums.TopicCategory;
import com.app.tweetgram.repository.projection.FollowedTopicProjection;
import com.app.tweetgram.repository.projection.NotInterestedTopicProjection;
import com.app.tweetgram.repository.projection.TopicProjection;
import com.app.tweetgram.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TopicMapper {

    private final BasicMapper basicMapper;
    private final TopicService topicService;

    public List<TopicResponse> getTopicsByIds(List<Long> topicsIds) {
        List<TopicProjection> topics = topicService.getTopicsByIds(topicsIds);
        return basicMapper.convertToResponseList(topics, TopicResponse.class);
    }

    public List<TopicsByCategoriesResponse> getTopicsByCategories(List<TopicCategory> categories) {
        return topicService.getTopicsByCategories(categories);
    }

    public List<TopicResponse> getFollowedTopics() {
        List<FollowedTopicProjection> topics = topicService.getFollowedTopics();
        return basicMapper.convertToResponseList(topics, TopicResponse.class);
    }

    public List<TopicResponse> getFollowedTopicsByUserId(Long userId) {
        List<TopicProjection> topics = topicService.getFollowedTopicsByUserId(userId);
        return basicMapper.convertToResponseList(topics, TopicResponse.class);
    }

    public List<TopicResponse> getNotInterestedTopics() {
        List<NotInterestedTopicProjection> topics = topicService.getNotInterestedTopics();
        return basicMapper.convertToResponseList(topics, TopicResponse.class);
    }

    public Boolean processNotInterestedTopic(Long topicId) {
        return topicService.processNotInterestedTopic(topicId);
    }

    public Boolean processFollowTopic(Long topicId) {
        return topicService.processFollowTopic(topicId);
    }
}
