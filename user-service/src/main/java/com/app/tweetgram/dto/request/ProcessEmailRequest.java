package com.app.tweetgram.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;

import static com.app.tweetgram.constants.ErrorMessage.EMAIL_NOT_VALID;

@Data
public class ProcessEmailRequest {
    @Email(regexp = ".+@.+\\..+", message = EMAIL_NOT_VALID)
    private String email;
}
