package com.app.tweetgram.controller.rest;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.response.MutedUserResponse;
import com.app.tweetgram.mapper.MuteUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_USER)
public class MuteUserController {

    private final MuteUserMapper muteUserMapper;

    @GetMapping(MUTED)
    public ResponseEntity<List<MutedUserResponse>> getMutedList(@PageableDefault(size = 15) Pageable pageable) {
        HeaderResponse<MutedUserResponse> response = muteUserMapper.getMutedList(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping(MUTED_USER_ID)
    public ResponseEntity<Boolean> processMutedList(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(muteUserMapper.processMutedList(userId));
    }
}
