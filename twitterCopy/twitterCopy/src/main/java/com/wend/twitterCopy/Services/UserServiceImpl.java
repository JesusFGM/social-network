package com.wend.twitterCopy.Services;

import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Entities.User;
import com.wend.twitterCopy.Repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User you are trying to find does not exist"));
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User you are trying to update does not exist"));
        BeanUtils.copyProperties(user, existingUser);
        return userRepository.saveAndFlush(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("User you are trying to delete does not exist");
        }
    }
    @Override
    public List<Twit> getUserTwits(Integer id) {
        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get().getUserTwits();
        } else{
            throw new NoSuchElementException("User does not exist.");
        }
    }
    @Override
    public void followUser(Integer followingId, Integer toFollowId) {
        User userFollowing = userRepository.findById(followingId).orElseThrow(NoSuchElementException::new);
        User userToFollow = userRepository.findById(toFollowId).orElseThrow(NoSuchElementException::new);

        userFollowing.addToListOfFollowing(userToFollow);
        userFollowing.setFollowing(userFollowing.getFollowing() + 1);

        userToFollow.addToListOfFollowers(userFollowing);
        userToFollow.setFollowers(userToFollow.getFollowers() + 1);

        userRepository.saveAndFlush(userFollowing);
        userRepository.saveAndFlush(userToFollow);

    }
    @Override
    public void unfollowUser(Integer unfollowingId, Integer toUnfollowId) {
        User userUnfollowing = userRepository.findById(unfollowingId).orElseThrow(NoSuchElementException::new);
        User userToUnfollow = userRepository.findById(toUnfollowId).orElseThrow(NoSuchElementException::new);

        userUnfollowing.removeFromListOfFollowing(userToUnfollow);
        userUnfollowing.setFollowing(userUnfollowing.getFollowing() - 1);

        userToUnfollow.removeFromListOfFollowers(userUnfollowing);
        userToUnfollow.setFollowers(userToUnfollow.getFollowers() - 1);

        userRepository.saveAndFlush(userUnfollowing);
        userRepository.saveAndFlush(userToUnfollow);
    }

    /*public List<String> sendMessage(Integer senderId, Integer receiverId, String message) {
        User senderUser = userRepository.findById(senderId).orElseThrow(NoSuchElementException::new);
        User receiverUser = userRepository.findById(receiverId).orElseThrow(NoSuchElementException::new);

        message = message + "   -- Sent by : " + senderUser.getUserName() + "  -- Received by : " + receiverUser.getUserName();


    }*/

    @Override
    public Set<User> getFollowers(Integer id) {
       User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
       return user.getListOfFollowers();
    }
}
