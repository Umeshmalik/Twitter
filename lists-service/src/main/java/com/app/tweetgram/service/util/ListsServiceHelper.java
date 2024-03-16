package com.app.tweetgram.service.util;

import com.app.tweetgram.dto.request.IdsRequest;
import com.app.tweetgram.dto.request.NotificationRequest;
import com.app.tweetgram.dto.response.lists.ListMemberResponse;
import com.app.tweetgram.dto.response.user.CommonUserResponse;
import com.app.tweetgram.enums.NotificationType;
import com.app.tweetgram.exception.ApiRequestException;
import com.app.tweetgram.feign.NotificationClient;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.repository.ListsFollowersRepository;
import com.app.tweetgram.repository.ListsMembersRepository;
import com.app.tweetgram.repository.ListsRepository;
import com.app.tweetgram.repository.PinnedListsRepository;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.tweetgram.constants.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class ListsServiceHelper {

    private final ListsRepository listsRepository;
    private final ListsFollowersRepository listsFollowersRepository;
    private final ListsMembersRepository listsMembersRepository;
    private final PinnedListsRepository pinnedListsRepository;
    private final NotificationClient notificationClient;
    private final UserClient userClient;

    public List<ListMemberResponse> getListMemberResponses(Long listId) {
        List<Long> membersIds = listsMembersRepository.getMembersIds(listId);
        return userClient.getListParticipantsByIds(new IdsRequest(membersIds));
    }

    public boolean isListIncludeUser(Long listId, Long memberId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return listsRepository.isListIncludeUser(listId, authUserId, memberId);
    }

    public void checkUserIsBlocked(Long userId, Long supposedBlockedUserId) {
        boolean isPresent = userClient.isUserBlocked(userId, supposedBlockedUserId);

        if (isPresent) {
            throw new ApiRequestException(String.format(USER_ID_BLOCKED, supposedBlockedUserId), HttpStatus.BAD_REQUEST);
        }
    }

    public void checkIsPrivateUserProfile(Long userId) {
        boolean isPrivateUserProfile = userClient.isUserHavePrivateProfile(userId);

        if (isPrivateUserProfile) {
            throw new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public void checkIsListPrivate(Long listId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        boolean isPublic = listsRepository.isListPrivate(listId, authUserId);

        if (isPublic && !isMyProfileFollowList(listId)) {
            throw new ApiRequestException(LIST_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public void checkIsListExist(Long listId, Long listOwnerId) {
        boolean isListExist = listsRepository.isListExist(listId, listOwnerId);

        if (!isListExist) {
            throw new ApiRequestException(LIST_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public void sendNotification(Long notifiedUserId, Long userId, Long listId) {
        NotificationRequest request = NotificationRequest.builder()
                .notificationType(NotificationType.LISTS)
                .notificationCondition(true)
                .notifiedUserId(notifiedUserId)
                .userId(userId)
                .listId(listId)
                .build();
        notificationClient.sendNotification(request);
    }

    public void validateListNameLength(String listName) {
        if (listName.length() == 0 || listName.length() > 25) {
            throw new ApiRequestException(INCORRECT_LIST_NAME_LENGTH, HttpStatus.BAD_REQUEST);
        }
    }

    public void validateListOwner(Long listOwnerId, Long authUserId) {
        if (!listOwnerId.equals(authUserId)) {
            throw new ApiRequestException(LIST_OWNER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public boolean isMyProfileFollowList(Long listId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return listsFollowersRepository.isListFollowed(authUserId, listId);
    }

    public CommonUserResponse getListOwnerById(Long userId) {
        return userClient.getListOwnerById(userId);
    }

    public boolean isListPinned(Long listId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return pinnedListsRepository.isListPinned(listId, authUserId);
    }
}
