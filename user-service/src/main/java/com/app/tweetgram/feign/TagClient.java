package com.app.tweetgram.feign;

import com.app.tweetgram.configuration.FeignConfiguration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static com.app.tweetgram.constants.FeignConstants.TAG_SERVICE;
import static com.app.tweetgram.constants.PathConstants.API_V1_TAGS;
import static com.app.tweetgram.constants.PathConstants.SEARCH_TEXT;

@FeignClient(value = TAG_SERVICE, path = API_V1_TAGS, configuration = FeignConfiguration.class)
public interface TagClient {

    @CircuitBreaker(name = TAG_SERVICE, fallbackMethod = "defaultEmptyArray")
    @GetMapping(SEARCH_TEXT)
    List<String> getTagsByText(@PathVariable("text") String text);

    default ArrayList<String> defaultEmptyArray(Throwable throwable) {
        return new ArrayList<>();
    }
}
