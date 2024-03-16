package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import static com.app.tweetgram.constants.FeignConstants.IMAGE_SERVICE;
import static com.app.tweetgram.constants.PathConstants.API_V1_IMAGE;
import static com.app.tweetgram.constants.PathConstants.UPLOAD;

@CircuitBreaker(name = IMAGE_SERVICE)
@FeignClient(value = IMAGE_SERVICE, path =API_V1_IMAGE, configuration = FeignConfiguration.class)
public interface ImageClient {
    @PostMapping(value = UPLOAD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(MultipartFile file);
}
