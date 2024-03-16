package com.app.tweetgram.service;

import com.app.tweetgram.dto.*;
import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.response.chat.ChatTweetUserResponse;
import com.app.tweetgram.dto.response.chat.ChatUserParticipantResponse;
import com.app.tweetgram.dto.response.lists.ListMemberResponse;
import com.app.tweetgram.dto.response.user.CommonUserResponse;
import com.app.tweetgram.dto.response.notification.NotificationUserResponse;
import com.app.tweetgram.dto.response.tweet.TweetAdditionalInfoUserResponse;
import com.app.tweetgram.dto.response.tweet.TweetAuthorResponse;
import com.app.tweetgram.dto.response.user.TaggedUserResponse;
import com.app.tweetgram.dto.response.user.UserChatResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserClientService {

    List<Long> getUserFollowersIds();

    HeaderResponse<UserChatResponse> searchUsersByUsername(String username, Pageable pageable);

    List<Long> getSubscribersByUserId(Long userId);

    Boolean isUserFollowByOtherUser(Long userId);

    Boolean isUserHavePrivateProfile(Long userId);

    Boolean isUserBlocked(Long userId, Long blockedUserId);

    Boolean isUserBlockedByMyProfile(Long userId);

    Boolean isMyProfileBlockedByUser(Long userId);

    void increaseNotificationsCount(Long userId);

    void increaseMentionsCount(Long userId);

    void updateLikeCount(boolean increase);

    void updateTweetCount(boolean increaseCount);

    void updateMediaTweetCount(boolean increaseCount);

    CommonUserResponse getListOwnerById(Long userId);

    List<ListMemberResponse> getListParticipantsByIds(IdsRequest request);

    List<ListMemberResponse> searchListMembersByUsername(String username);

    NotificationUserResponse getNotificationUser(Long userId);

    TweetAuthorResponse getTweetAuthor(Long userId);

    TweetAdditionalInfoUserResponse getTweetAdditionalInfoUser(Long userId);

    HeaderResponse<UserResponse> getUsersByIds(IdsRequest request, Pageable pageable);

    List<TaggedUserResponse> getTaggedImageUsers(IdsRequest request);

    void updatePinnedTweetId(Long tweetId);

    Long getUserPinnedTweetId(Long userId);

    List<Long> getValidTweetUserIds(IdsRequest request, String text);

    List<Long> getValidUserIds(IdsRequest request);

    ChatUserParticipantResponse getChatParticipant(Long userId);

    Boolean isUserExists(Long userId);

    UserResponse getUserResponseById(Long userId);

    Long getUserIdByUsername(String username);

    ChatTweetUserResponse getChatTweetUser(Long userId);

    List<Long> validateChatUsersIds(IdsRequest request);

    List<NotificationUserResponse> getUsersWhichUserSubscribed();

    List<Long> getUserIdsWhichUserSubscribed();

    void resetNotificationCount();

    void resetMentionCount();
}
