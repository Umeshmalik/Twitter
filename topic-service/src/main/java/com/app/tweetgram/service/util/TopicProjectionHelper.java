package com.app.tweetgram.service.util;

import com.app.tweetgram.repository.TopicFollowersRepository;
import com.app.tweetgram.repository.TopicNotInterestedRepository;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicProjectionHelper {

    private final TopicFollowersRepository topicFollowersRepository;
    private final TopicNotInterestedRepository topicNotInterestedRepository;

    public boolean isTopicFollowed(Long topicId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return topicFollowersRepository.isTopicFollowed(authUserId, topicId);
    }

    public boolean isTopicNotInterested(Long topicId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return topicNotInterestedRepository.isTopicNotInterested(authUserId, topicId);
    }
}
