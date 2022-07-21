package com.wend.twitterCopy.Services;

import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User findUserById(int id);

    User addUser(User user);

    User updateUser(Integer id, User user);

    void deleteUser(Integer id);

    List<Twit> getUserTwits(Integer id);

    void followUser(Integer userId, Integer toFollowId);

    void unfollowUser(Integer unfollowingUserId, Integer toUnfollowId);

    Set<User> getFollowers(Integer id);
}
