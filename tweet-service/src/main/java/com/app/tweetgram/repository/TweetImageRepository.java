package com.app.tweetgram.repository;

import com.app.tweetgram.model.TweetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetImageRepository extends JpaRepository<TweetImage, Long> {
}
