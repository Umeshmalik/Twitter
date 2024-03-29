package com.app.tweetgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import static com.app.tweetgram.constants.PathConstants.API_V1_WEBSOCKET;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_WEBSOCKET)
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public void send(@RequestParam("destination") String destination, @RequestBody Object request) {
        messagingTemplate.convertAndSend(destination, request);
    }
}
