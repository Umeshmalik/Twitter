package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.TagResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagMapper {

    private final BasicMapper basicMapper;
    private final TagService tagService;

    public List<TagResponse> getTags() {
        return basicMapper.convertToResponseList(tagService.getTags(), TagResponse.class);
    }

    public HeaderResponse<TagResponse> getTrends(Pageable pageable) {
        return basicMapper.getHeaderResponse(tagService.getTrends(pageable), TagResponse.class);
    }

    public List<TweetResponse> getTweetsByTag(String tagName) {
        return tagService.getTweetsByTag(tagName);
    }
}
