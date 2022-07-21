package com.wend.twitterCopy.Controllers;

import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Entities.User;
import com.wend.twitterCopy.Services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
       userService.deleteUser(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}/twits")
    public ResponseEntity<List<Twit>> getUserTwits(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserTwits(id));
    }

    @PutMapping("{followingId}/follow/{toFollowId}")
    public ResponseEntity<?> updateFollow(@PathVariable Integer followingId, @PathVariable Integer toFollowId) {
        userService.followUser(followingId, toFollowId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{unfollowingId}/unfollow/{toUnfollowId}")
    public ResponseEntity<?> updateUnfollow(@PathVariable Integer unfollowingId, @PathVariable Integer toUnfollowId) {
        userService.unfollowUser(unfollowingId, toUnfollowId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}/followers")
    public ResponseEntity<Set<User>> getListOfFollowers(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getFollowers(id));
    }
}
