package com.app.tweetgram.controller.rest;

import com.app.tweetgram.dto.request.EndRegistrationRequest;
import com.app.tweetgram.dto.request.ProcessEmailRequest;
import com.app.tweetgram.dto.request.RegistrationRequest;
import com.app.tweetgram.dto.response.AuthenticationResponse;
import com.app.tweetgram.mapper.RegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.app.tweetgram.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_AUTH)
public class RegistrationController {

    private final RegistrationMapper registrationMapper;

    @PostMapping(REGISTRATION_CHECK)
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest request, BindingResult bindingResult) {
        return ResponseEntity.ok(registrationMapper.registration(request, bindingResult));
    }

    @PostMapping(REGISTRATION_CODE)
    public ResponseEntity<String> sendRegistrationCode(@Valid @RequestBody ProcessEmailRequest request, BindingResult bindingResult) {
        return ResponseEntity.ok(registrationMapper.sendRegistrationCode(request.getEmail(), bindingResult));
    }

    @GetMapping(REGISTRATION_ACTIVATE_CODE)
    public ResponseEntity<String> checkRegistrationCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(registrationMapper.checkRegistrationCode(code));
    }

    @PostMapping(REGISTRATION_CONFIRM)
    public ResponseEntity<AuthenticationResponse> endRegistration(@Valid @RequestBody EndRegistrationRequest request, BindingResult bindingResult) {
        return ResponseEntity.ok(registrationMapper.endRegistration(request, bindingResult));
    }
}
