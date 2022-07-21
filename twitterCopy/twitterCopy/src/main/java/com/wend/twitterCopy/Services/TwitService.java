package com.wend.twitterCopy.Services;

import com.wend.twitterCopy.Entities.Twit;

import java.util.List;

public interface TwitService {
    List<Twit> getAllTwits();

    Twit getTwitById(int id);

    Twit updateTwit(Integer twitId, Twit twit);

    void deleteTwit(Integer id);

    Twit postTwit(Integer userId, Twit twit);

    List<Twit> findTwitByUserId(Integer id);
}
