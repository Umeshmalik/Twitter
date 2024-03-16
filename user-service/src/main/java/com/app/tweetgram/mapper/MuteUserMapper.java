package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.MutedUserResponse;
import com.app.tweetgram.repository.projection.MutedUserProjection;
import com.app.tweetgram.service.MuteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuteUserMapper {

    private final BasicMapper basicMapper;
    private final MuteUserService muteUserService;

    public HeaderResponse<MutedUserResponse> getMutedList(Pageable pageable) {
        Page<MutedUserProjection> mutedList = muteUserService.getMutedList(pageable);
        return basicMapper.getHeaderResponse(mutedList, MutedUserResponse.class);
    }

    public Boolean processMutedList(Long userId) {
        return muteUserService.processMutedList(userId);
    }
}
