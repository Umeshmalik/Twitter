package com.app.tweetgram.service;

import com.app.tweetgram.dto.request.SearchTermsRequest;
import com.app.tweetgram.model.User;
import com.app.tweetgram.repository.projection.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserProfileProjection getUserById(Long userId);

    Page<UserProjection> getUsers(Pageable pageable);

    List<UserProjection> getRelevantUsers();

    <T> Page<T> searchUsersByUsername(String username, Pageable pageable, Class<T> type);

    Map<String, Object> searchByText(String text);

    List<CommonUserProjection> getSearchResults(SearchTermsRequest request);

    Boolean startUseTwitter();

    AuthUserProjection updateUserProfile(User userInfo);

    Boolean processSubscribeToNotifications(Long userId);

    Long processPinTweet(Long tweetId);

    UserDetailProjection getUserDetails(Long userId);

    String uploadImage(MultipartFile file);
}
