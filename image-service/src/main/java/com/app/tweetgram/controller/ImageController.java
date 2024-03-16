package com.app.tweetgram.controller;

import com.app.tweetgram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.app.tweetgram.constants.PathConstants.API_V1_IMAGE;
import static com.app.tweetgram.constants.PathConstants.UPLOAD;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_IMAGE)
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = UPLOAD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart("file") MultipartFile file) {
        String url = imageService.uploadImage(file);
        return url;
    }
}
