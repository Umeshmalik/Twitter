package com.app.tweetgram.service;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.tweet.TweetResponse;
import com.app.tweetgram.dto.response.lists.ListMemberResponse;
import com.app.tweetgram.dto.request.UserToListsRequest;
import com.app.tweetgram.model.Lists;
import com.app.tweetgram.repository.projection.*;
import com.app.tweetgram.repository.projection.PinnedListProjection;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ListsService {

    List<ListProjection> getAllTweetLists();

    List<ListUserProjection> getUserTweetLists();

    List<PinnedListProjection> getUserPinnedLists();

    BaseListProjection getListById(Long listId);

    ListUserProjection createTweetList(Lists lists);

    List<ListProjection> getUserTweetListsById(Long userId);

    List<ListProjection> getTweetListsWhichUserIn();

    BaseListProjection editTweetList(Lists lists);

    String deleteList(Long listId);

    ListUserProjection followList(Long listId);

    PinnedListProjection pinList(Long listId);

    List<Map<String, Object>> getListsToAddUser(Long userId);

    String addUserToLists(UserToListsRequest userToListsRequest);

    Boolean addUserToList(Long userId, Long listId);

    HeaderResponse<TweetResponse> getTweetsByListId(Long listId, Pageable pageable);

    BaseListProjection getListDetails(Long listId);

    List<ListMemberResponse> getListFollowers(Long listId, Long listOwnerId);

    List<ListMemberResponse> getListMembers(Long listId, Long listOwnerId);

    List<ListMemberResponse> searchListMembersByUsername(Long listId, String username);
}
