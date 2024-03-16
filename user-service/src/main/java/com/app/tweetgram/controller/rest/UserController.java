package com.app.tweetgram.controller.rest;

import com.app.tweetgram.dto.HeaderResponse;
import com.app.tweetgram.dto.request.SearchTermsRequest;
import com.app.tweetgram.dto.response.user.CommonUserResponse;
import com.app.tweetgram.dto.response.user.UserResponse;
import com.app.tweetgram.dto.request.UserRequest;
import com.app.tweetgram.dto.response.*;
import com.app.tweetgram.mapper.AuthenticationMapper;
import com.app.tweetgram.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_USER)
public class UserController {

    private final UserMapper userMapper;
    private final AuthenticationMapper authenticationMapper;

    @GetMapping(TOKEN)
    public ResponseEntity<AuthenticationResponse> getUserByToken() {
        return ResponseEntity.ok(authenticationMapper.getUserByToken());
    }

    @GetMapping(USER_ID)
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userMapper.getUserById(userId));
    }

    @GetMapping(ALL)
    public ResponseEntity<List<UserResponse>> getUsers(@PageableDefault(size = 15) Pageable pageable) {
        HeaderResponse<UserResponse> response = userMapper.getUsers(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping(RELEVANT)
    public ResponseEntity<List<UserResponse>> getRelevantUsers() {
        return ResponseEntity.ok(userMapper.getRelevantUsers());
    }

    @GetMapping(SEARCH_USERNAME)
    public ResponseEntity<List<UserResponse>> searchUsersByUsername(@PathVariable("username") String username,
                                                                    @PageableDefault(size = 15) Pageable pageable) {
        HeaderResponse<UserResponse> response = userMapper.searchUsersByUsername(username, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping(SEARCH_TEXT)
    public ResponseEntity<SearchResultResponse> searchByText(@PathVariable("text") String text) {
        return ResponseEntity.ok(userMapper.searchByText(text));
    }

    @PostMapping(SEARCH_RESULTS)
    public ResponseEntity<List<CommonUserResponse>> getSearchResults(@RequestBody SearchTermsRequest request) {
        return ResponseEntity.ok(userMapper.getSearchResults(request));
    }

    @PostMapping(value = UPLOAD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file){
        return ResponseEntity.ok(userMapper.uploadImage(file));
    }

    @GetMapping(START)
    public ResponseEntity<Boolean> startUseTwitter() {
        return ResponseEntity.ok(userMapper.startUseTwitter());
    }

    @PutMapping
    public ResponseEntity<AuthUserResponse> updateUserProfile(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userMapper.updateUserProfile(userRequest));
    }

    @GetMapping(SUBSCRIBE_USER_ID)
    public ResponseEntity<Boolean> processSubscribeToNotifications(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userMapper.processSubscribeToNotifications(userId));
    }

    @GetMapping(PIN_TWEET_ID)
    public ResponseEntity<Long> processPinTweet(@PathVariable("tweetId") Long tweetId) {
        return ResponseEntity.ok(userMapper.processPinTweet(tweetId));
    }

    @GetMapping(DETAILS_USER_ID)
    public ResponseEntity<UserDetailResponse> getUserDetails(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userMapper.getUserDetails(userId));
    }
}
