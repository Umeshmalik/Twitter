package com.app.tweetgram.mapper;

import com.app.tweetgram.dto.request.SettingsRequest;
import com.app.tweetgram.dto.response.AuthenticationResponse;
import com.app.tweetgram.dto.response.UserPhoneResponse;
import com.app.tweetgram.enums.BackgroundColorType;
import com.app.tweetgram.enums.ColorSchemeType;
import com.app.tweetgram.service.UserSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserSettingsMapper {

    private final AuthenticationMapper authenticationMapper;
    private final UserSettingsService userSettingsService;

    public String updateUsername(SettingsRequest request) {
        return userSettingsService.updateUsername(request.getUsername());
    }

    public AuthenticationResponse updateEmail(SettingsRequest request) {
        Map<String, Object> stringObjectMap = userSettingsService.updateEmail(request.getEmail());
        AuthenticationResponse authenticationResponse = authenticationMapper.getAuthenticationResponse(stringObjectMap);
        authenticationResponse.getUser().setEmail(request.getEmail());
        return authenticationResponse;
    }

    public UserPhoneResponse updatePhone(SettingsRequest request) {
        Map<String, Object> phoneParams = userSettingsService.updatePhone(request.getCountryCode(), request.getPhone());
        return new UserPhoneResponse((String) phoneParams.get("countryCode"), (Long) phoneParams.get("phone"));
    }

    public String updateCountry(SettingsRequest request) {
        return userSettingsService.updateCountry(request.getCountry());
    }

    public String updateGender(SettingsRequest request) {
        return userSettingsService.updateGender(request.getGender());
    }

    public String updateLanguage(SettingsRequest request) {
        return userSettingsService.updateLanguage(request.getLanguage());
    }

    public boolean updateDirectMessageRequests(SettingsRequest request) {
        return userSettingsService.updateDirectMessageRequests(request.isMutedDirectMessages());
    }

    public boolean updatePrivateProfile(SettingsRequest request) {
        return userSettingsService.updatePrivateProfile(request.isPrivateProfile());
    }

    public ColorSchemeType updateColorScheme(SettingsRequest request) {
        return userSettingsService.updateColorScheme(request.getColorScheme());
    }

    public BackgroundColorType updateBackgroundColor(SettingsRequest request) {
        return userSettingsService.updateBackgroundColor(request.getBackgroundColor());
    }
}
