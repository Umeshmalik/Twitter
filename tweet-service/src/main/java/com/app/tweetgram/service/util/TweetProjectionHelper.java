package com.app.tweetgram.service.util;

import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.response.chat.ChatTweetUserResponse;
import com.app.tweetgram.dto.response.tweet.TweetAdditionalInfoUserResponse;
import com.app.tweetgram.dto.response.tweet.TweetAuthorResponse;
import com.app.tweetgram.dto.response.tweet.TweetListResponse;
import com.app.tweetgram.dto.response.user.TaggedUserResponse;
import com.app.tweetgram.feign.ListsClient;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.repository.BookmarkRepository;
import com.app.tweetgram.repository.LikeTweetRepository;
import com.app.tweetgram.repository.RetweetRepository;
import com.app.tweetgram.repository.TweetRepository;
import com.app.tweetgram.repository.projection.TweetProjection;
import com.app.tweetgram.repository.projection.TweetUserProjection;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TweetProjectionHelper {

    private final TweetRepository tweetRepository;
    private final LikeTweetRepository likeTweetRepository;
    private final RetweetRepository retweetRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserClient userClient;
    private final ListsClient listsClient;

    public TweetProjection getTweetProjection(Long tweetId) {
        return tweetRepository.getTweetById(tweetId, TweetProjection.class).get();
    }

    public TweetUserProjection getTweetUserProjection(Long tweetId) {
        return tweetRepository.getTweetById(tweetId, TweetUserProjection.class).get();
    }

    public List<TaggedUserResponse> getTaggedImageUsers(Long tweetId) {
        List<Long> taggedImageUserIds = tweetRepository.getTaggedImageUserIds(tweetId);
        return userClient.getTaggedImageUsers(new IdsRequest(taggedImageUserIds));
    }

    public boolean isUserLikedTweet(Long tweetId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return likeTweetRepository.isUserLikedTweet(authUserId, tweetId);
    }

    public boolean isUserRetweetedTweet(Long tweetId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return retweetRepository.isUserRetweetedTweet(authUserId, tweetId);
    }

    public boolean isUserBookmarkedTweet(Long tweetId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return bookmarkRepository.isUserBookmarkedTweet(authUserId, tweetId);
    }

    public boolean isUserFollowByOtherUser(Long userId) {
        return userClient.isUserFollowByOtherUser(userId);
    }

    public TweetAuthorResponse getTweetAuthor(Long userId) {
        return userClient.getTweetAuthor(userId);
    }

    public TweetListResponse getTweetList(Long listId) {
        TweetListResponse tweetList = listsClient.getTweetList(listId);

        if (tweetList.getId() != null) {
            return tweetList;
        } else {
            return null;
        }
    }

    public TweetAdditionalInfoUserResponse getTweetAdditionalInfoUser(Long userId) {
        return userClient.getTweetAdditionalInfoUser(userId);
    }

    public ChatTweetUserResponse getChatTweetUser(Long userId) {
        return userClient.getChatTweetUser(userId);
    }
}
