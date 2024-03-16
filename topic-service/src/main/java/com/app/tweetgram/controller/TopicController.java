package com.app.tweetgram.controller;

import com.app.tweetgram.dto.request.SuggestedTopicsRequest;
import com.app.tweetgram.dto.request.TopicsCategoriesRequest;
import com.app.tweetgram.dto.response.TopicResponse;
import com.app.tweetgram.dto.response.TopicsByCategoriesResponse;
import com.app.tweetgram.mapper.TopicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_TOPICS)
public class TopicController {

    private final TopicMapper topicMapper;

    @PostMapping(SUGGESTED)
    public ResponseEntity<List<TopicResponse>> getTopicsByIds(@RequestBody SuggestedTopicsRequest request) {
        return ResponseEntity.ok(topicMapper.getTopicsByIds(request.getTopicsIds()));
    }

    @PostMapping(CATEGORY)
    public ResponseEntity<List<TopicsByCategoriesResponse>> getTopicsByCategories(@RequestBody TopicsCategoriesRequest request) {
        return ResponseEntity.ok(topicMapper.getTopicsByCategories(request.getCategories()));
    }

    @GetMapping(FOLLOWED)
    public ResponseEntity<List<TopicResponse>> getFollowedTopics() {
        return ResponseEntity.ok(topicMapper.getFollowedTopics());
    }

    @GetMapping(FOLLOWED_USER_ID)
    public ResponseEntity<List<TopicResponse>> getFollowedTopicsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(topicMapper.getFollowedTopicsByUserId(userId));
    }

    @GetMapping(NOT_INTERESTED)
    public ResponseEntity<List<TopicResponse>> getNotInterestedTopics() {
        return ResponseEntity.ok(topicMapper.getNotInterestedTopics());
    }

    @GetMapping(NOT_INTERESTED_TOPIC_ID)
    public ResponseEntity<Boolean> processNotInterestedTopic(@PathVariable("topicId") Long topicId) {
        return ResponseEntity.ok(topicMapper.processNotInterestedTopic(topicId));
    }

    @GetMapping(FOLLOW_TOPIC_ID)
    public ResponseEntity<Boolean> processFollowTopic(@PathVariable("topicId") Long topicId) {
        return ResponseEntity.ok(topicMapper.processFollowTopic(topicId));
    }
}
