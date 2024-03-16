package com.app.tweetgram.controller.api;

import com.app.tweetgram.dto.response.user.UserPrincipalResponse;
import com.app.tweetgram.mapper.BasicMapper;
import com.app.tweetgram.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.tweetgram.constants.PathConstants.API_V1_AUTH;
import static com.app.tweetgram.constants.PathConstants.USER_EMAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_AUTH)
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;
    private final BasicMapper mapper;

    @GetMapping(USER_EMAIL)
    public UserPrincipalResponse getUserPrincipalByEmail(@PathVariable("email") String email) {
        return mapper.convertToResponse(authenticationService.getUserPrincipalByEmail(email), UserPrincipalResponse.class);
    }
}
