package com.wend.twitterCopy.Repositories;

import com.wend.twitterCopy.Entities.Message;
import com.wend.twitterCopy.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByReceiver(User user);

    List<Message> findBySender(User user);


}
