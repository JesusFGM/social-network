package com.wend.twitterCopy.Services;

import com.wend.twitterCopy.Entities.Message;
import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Entities.User;
import com.wend.twitterCopy.Repositories.MessageRepository;
import com.wend.twitterCopy.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public Date getDate(){
        Date date = new Date();
        return date;
    }

    public List<Message> findMessageByReceiver(Integer id) {
        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user.setUserId(id);
            return messageRepository.findByReceiver(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Message> findMessageBySender(Integer id) {
        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user.setUserId(id);
            return messageRepository.findBySender(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Message sendMessage(Integer senderId, Integer receiverId, Message message) {
        User sender =  userRepository.findById(senderId).orElseThrow(NoSuchElementException::new);
        User receiver =  userRepository.findById(receiverId).orElseThrow(NoSuchElementException::new);

        sender.addToMessagesSent(message);
        receiver.addToMessagesReceived(message);

        message.setSentDate(getDate());
        message.setReceiver(receiver);
        message.setSender(sender);
        messageRepository.saveAndFlush(message);

        return message;
    }
}
