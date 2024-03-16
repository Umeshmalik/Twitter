package com.app.tweetgram.service;

import com.app.tweetgram.repository.projection.MutedUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MuteUserService {

    Page<MutedUserProjection> getMutedList(Pageable pageable);

    Boolean processMutedList(Long userId);
}
