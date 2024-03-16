package com.app.tweetgram.dto;

import lombok.Data;

@Data
public class TagResponse {
    private Long id;
    private String tagName;
    private Long tweetsQuantity;
}
