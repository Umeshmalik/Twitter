package com.app.tweetgram.service.impl;

import com.app.tweetgram.dto.response.tweet.TweetListResponse;
import com.app.tweetgram.dto.response.notification.NotificationListResponse;
import com.app.tweetgram.feign.UserClient;
import com.app.tweetgram.mapper.BasicMapper;
import com.app.tweetgram.repository.ListsRepository;
import com.app.tweetgram.repository.projection.NotificationListProjection;
import com.app.tweetgram.repository.projection.TweetListProjection;
import com.app.tweetgram.service.ListsClientService;
import com.app.tweetgram.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListsClientServiceImpl implements ListsClientService {

    private final ListsRepository listsRepository;
    private final UserClient userClient;
    private final BasicMapper basicMapper;

    @Override
    public NotificationListResponse getNotificationList(Long listId) {
        NotificationListProjection list = listsRepository.getListById(listId, NotificationListProjection.class);
        return basicMapper.convertToResponse(list, NotificationListResponse.class);
    }

    @Override
    public TweetListResponse getTweetList(Long listId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        Optional<TweetListProjection> list = listsRepository.getListById(listId, authUserId, TweetListProjection.class);

        if (list.isEmpty()) {
            return new TweetListResponse();
        }
        if (userClient.isUserBlocked(list.get().getListOwnerId(), authUserId)) {
            return new TweetListResponse();
        }
        if (!authUserId.equals(list.get().getListOwnerId())) {
            if (userClient.isUserHavePrivateProfile(list.get().getListOwnerId())) {
                return new TweetListResponse();
            }
        }
        return basicMapper.convertToResponse(list.get(), TweetListResponse.class);
    }
}
