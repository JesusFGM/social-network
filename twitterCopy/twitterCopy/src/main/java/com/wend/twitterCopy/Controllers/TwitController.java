package com.wend.twitterCopy.Controllers;

import com.wend.twitterCopy.Entities.Twit;
import com.wend.twitterCopy.Services.TwitService;
import com.wend.twitterCopy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("twits")
public class TwitController {

    @Autowired
    TwitService twitService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Twit>> getAllTwits() {
        return ResponseEntity.ok(twitService.getAllTwits());
    }

    @GetMapping("{id}")
    public ResponseEntity<Twit> getTwitById(@PathVariable int id) {
        return ResponseEntity.ok(twitService.getTwitById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Twit> updateTwit(@PathVariable int id, @RequestBody Twit twit) {
        return ResponseEntity.ok(twitService.updateTwit(id, twit));
    }

    @DeleteMapping("{id}")
    public void deleteTwit(@PathVariable Integer id) {
        twitService.deleteTwit(id);
    }

    @PostMapping("/user/{userId}/twits")
    public ResponseEntity<Twit> postTwit(@PathVariable Integer userId, @RequestBody Twit twit) {
        return ResponseEntity.ok(twitService.postTwit(userId, twit));
    }

    @GetMapping("user/{userId}/twits")
    public ResponseEntity<List<Twit>> getUserTwits(Integer userId) {
        return ResponseEntity.ok(userService.findUserById(userId).getUserTwits());
    }

}
