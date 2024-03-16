package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.repository.projection.BookmarkProjection;
import com.app.tweetgram.repository.projection.TweetProjection;
import com.app.tweetgram.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {

    private final BasicMapper basicMapper;
    private final BookmarkService bookmarkService;

    public HeaderResponse<TweetResponse> getUserBookmarks(Pageable pageable) {
        Page<BookmarkProjection> bookmarks = bookmarkService.getUserBookmarks(pageable);
        List<TweetProjection> tweets = new ArrayList<>();
        bookmarks.getContent().forEach(bookmark -> tweets.add(bookmark.getTweet()));
        return basicMapper.getHeaderResponse(tweets, bookmarks.getTotalPages(), TweetResponse.class);
    }

    public Boolean processUserBookmarks(Long tweetId) {
        return bookmarkService.processUserBookmarks(tweetId);
    }

    public Boolean getIsTweetBookmarked(Long tweetId) {
        return bookmarkService.getIsTweetBookmarked(tweetId);
    }
}
