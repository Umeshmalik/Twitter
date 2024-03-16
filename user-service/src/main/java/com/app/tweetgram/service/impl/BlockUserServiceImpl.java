package com.app.tweetgram.service.impl;

import com.app.tweetgram.repository.BlockUserRepository;
import com.app.tweetgram.repository.FollowerUserRepository;
import com.app.tweetgram.repository.projection.BlockedUserProjection;
import com.app.tweetgram.service.AuthenticationService;
import com.app.tweetgram.service.BlockUserService;
import com.app.tweetgram.service.util.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlockUserServiceImpl implements BlockUserService {

    private final AuthenticationService authenticationService;
    private final BlockUserRepository blockUserRepository;
    private final FollowerUserRepository followerUserRepository;
    private final UserServiceHelper userServiceHelper;

    @Override
    public Page<BlockedUserProjection> getBlockList(Pageable pageable) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return blockUserRepository.getUserBlockListById(authUserId, pageable);
    }

    @Override
    @Transactional
    public Boolean processBlockList(Long userId) {
        userServiceHelper.checkIsUserExist(userId);
        Long authUserId = authenticationService.getAuthenticatedUserId();
        boolean isUserBlocked = blockUserRepository.isUserBlocked(authUserId, userId);

        if (isUserBlocked) {
            blockUserRepository.unblockUser(authUserId, userId);
            return false;
        } else {
            blockUserRepository.blockUser(authUserId, userId);
            followerUserRepository.unfollow(authUserId, userId);
            followerUserRepository.unfollow(userId, authUserId);
            return true;
        }
    }
}
