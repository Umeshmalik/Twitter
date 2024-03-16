package com.app.tweetgram.service;

import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<Tag> getTags();

    Page<Tag> getTrends(Pageable pageable);

    List<TweetResponse> getTweetsByTag(String tagName);
}
