package com.app.tweetgram.service.impl;

import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.exception.ApiRequestException;
import com.app.tweetgram.feign.TweetClient;
import com.app.tweetgram.model.Tag;
import com.app.tweetgram.repository.TagRepository;
import com.app.tweetgram.repository.TweetTagRepository;
import com.app.tweetgram.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.tweetgram.constants.ErrorMessage.TAG_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TweetTagRepository tweetTagRepository;
    private final TweetClient tweetClient;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findTop5ByOrderByTweetsQuantityDesc();
    }

    @Override
    public Page<Tag> getTrends(Pageable pageable) {
        return tagRepository.findByOrderByTweetsQuantityDesc(pageable);
    }

    @Override
    public List<TweetResponse> getTweetsByTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName)
                .orElseThrow(() -> new ApiRequestException(TAG_NOT_FOUND, HttpStatus.NOT_FOUND));
        List<Long> tweetIds = tweetTagRepository.getTweetIdsByTagId(tag.getId());
        return tweetClient.getTweetsByIds(new IdsRequest(tweetIds));
    }
}
