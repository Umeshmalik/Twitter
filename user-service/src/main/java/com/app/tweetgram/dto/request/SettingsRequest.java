package com.app.tweetgram.dto.request;

import com.app.tweetgram.enums.BackgroundColorType;
import com.app.tweetgram.enums.ColorSchemeType;
import lombok.Data;

@Data
public class SettingsRequest {
    private String username;
    private String email;
    private String countryCode;
    private Long phone;
    private String country;
    private String gender;
    private String language;
    private boolean mutedDirectMessages;
    private boolean privateProfile;
    private BackgroundColorType backgroundColor;
    private ColorSchemeType colorScheme;
}
