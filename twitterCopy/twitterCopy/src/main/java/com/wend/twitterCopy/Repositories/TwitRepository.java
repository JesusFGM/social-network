package com.wend.twitterCopy.Repositories;

import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TwitRepository extends JpaRepository<Twit, Integer> {
    List<Twit> findByUser(User user); //find all twits with the given user id.

    // query = "SELECT t FROM twits t WHERE t.user_id = ?")


}
