package com.app.tweetgram.service.impl;

import com.app.tweetgram.model.Bookmark;
import com.app.tweetgram.repository.BookmarkRepository;
import com.app.tweetgram.repository.projection.BookmarkProjection;
import com.app.tweetgram.service.BookmarkService;
import com.app.tweetgram.service.util.TweetValidationHelper;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final TweetValidationHelper tweetValidationHelper;

    @Override
    public Page<BookmarkProjection> getUserBookmarks(Pageable pageable) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return bookmarkRepository.getUserBookmarks(authUserId, pageable);
    }

    @Override
    @Transactional
    public Boolean processUserBookmarks(Long tweetId) {
        tweetValidationHelper.checkValidTweet(tweetId);
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        Bookmark bookmark = bookmarkRepository.getUserBookmark(authUserId, tweetId);

        if (bookmark != null) {
            bookmarkRepository.delete(bookmark);
            return false;
        } else {
            Bookmark newBookmark = new Bookmark(authUserId, tweetId);
            bookmarkRepository.save(newBookmark);
            return true;
        }
    }

    @Override
    public Boolean getIsTweetBookmarked(Long tweetId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return bookmarkRepository.isUserBookmarkedTweet(authUserId, tweetId);
    }
}
