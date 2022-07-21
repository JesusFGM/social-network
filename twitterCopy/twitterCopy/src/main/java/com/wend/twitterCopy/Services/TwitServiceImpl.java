package com.wend.twitterCopy.Services;

import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Entities.User;
import com.wend.twitterCopy.Repositories.TwitRepository;
import com.wend.twitterCopy.Repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TwitServiceImpl implements TwitService {

    @Autowired
    TwitRepository twitRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Twit> getAllTwits() {
        return twitRepository.findAll();
    }

    @Override
    public Twit getTwitById(int id) {
        return twitRepository.findById(id).orElseThrow(() -> new NoSuchElementException("The twit you are trying to find does not exist."));
    }

    @Override
    public Twit updateTwit(Integer twitId, Twit twit) {
        Twit existingTwit = twitRepository.findById(twitId).orElseThrow(() -> new NoSuchElementException("The twit you are trying to update does not exist."));
        BeanUtils.copyProperties(existingTwit, twit);
        return twitRepository.saveAndFlush(existingTwit);
    }

    @Override
    public void deleteTwit(Integer id) {
        twitRepository.deleteById(id);
    }

    @Override
    public Twit postTwit(Integer userId, Twit twit) {
        if(userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            twit.setUser(user);
            return twitRepository.saveAndFlush(twit);
        } else {
            throw new NoSuchElementException("Non existent user cannot post a twit.");
        }
    }

    @Override
    public List<Twit> findTwitByUserId(Integer id) {
        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user.setUserId(id);
            return twitRepository.findByUser(user);
        } else {
            throw new NoSuchElementException();
        }
    }
}
