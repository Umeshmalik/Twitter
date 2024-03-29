package com.app.tweetgram.controller.api;

import com.app.tweetgram.dto.request.TweetTextRequest;
import com.app.tweetgram.service.TagClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_TAGS)
public class TagApiController {

    private final TagClientService tagClientService;

    @GetMapping(SEARCH_TEXT)
    public List<String> getTagsByText(@PathVariable("text") String text) {
        return tagClientService.getTagsByText(text);
    }

    @PostMapping(PARSE_TWEET_ID)
    public void parseHashtagsFromText(@PathVariable("tweetId") Long tweetId, @RequestBody TweetTextRequest request) {
        tagClientService.parseHashtagsFromText(tweetId, request.getText());
    }

    @DeleteMapping(DELETE_TWEET_ID)
    public void deleteTagsByTweetId(@PathVariable("tweetId") Long tweetId) {
        tagClientService.deleteTagsByTweetId(tweetId);
    }
}
