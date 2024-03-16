package com.app.tweetgram.controller.rest;

import com.app.tweetgram.dto.request.SettingsRequest;
import com.app.tweetgram.dto.response.AuthenticationResponse;
import com.app.tweetgram.dto.response.UserPhoneResponse;
import com.app.tweetgram.enums.BackgroundColorType;
import com.app.tweetgram.enums.ColorSchemeType;
import com.app.tweetgram.mapper.UserSettingsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_USER_SETTINGS_UPDATE)
public class UserSettingsController {

    private final UserSettingsMapper userSettingsMapper;

    @PutMapping(USERNAME)
    public ResponseEntity<String> updateUsername(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateUsername(request));
    }

    @PutMapping(EMAIL)
    public ResponseEntity<AuthenticationResponse> updateEmail(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateEmail(request));
    }

    @PutMapping(PHONE)
    public ResponseEntity<UserPhoneResponse> updatePhone(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updatePhone(request));
    }

    @PutMapping(COUNTRY)
    public ResponseEntity<String> updateCountry(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateCountry(request));
    }

    @PutMapping(GENDER)
    public ResponseEntity<String> updateGender(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateGender(request));
    }

    @PutMapping(LANGUAGE)
    public ResponseEntity<String> updateLanguage(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateLanguage(request));
    }

    @PutMapping(DIRECT)
    public ResponseEntity<Boolean> updateDirectMessageRequests(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateDirectMessageRequests(request));
    }

    @PutMapping(PRIVATE)
    public ResponseEntity<Boolean> updatePrivateProfile(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updatePrivateProfile(request));
    }

    @PutMapping(COLOR_SCHEME)
    public ResponseEntity<ColorSchemeType> updateColorScheme(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateColorScheme(request));
    }

    @PutMapping(BACKGROUND_COLOR)
    public ResponseEntity<BackgroundColorType> updateBackgroundColor(@RequestBody SettingsRequest request) {
        return ResponseEntity.ok(userSettingsMapper.updateBackgroundColor(request));
    }
}
