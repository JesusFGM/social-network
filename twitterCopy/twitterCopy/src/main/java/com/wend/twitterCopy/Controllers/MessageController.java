package com.wend.twitterCopy.Controllers;

import com.wend.twitterCopy.Entities.Message;
import com.wend.twitterCopy.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    MessageService messageService;


    @GetMapping("{receiverId}/received")
    public ResponseEntity<List<Message>> getReceivedMessages(@PathVariable Integer receiverId) {
        return ResponseEntity.ok(messageService.findMessageByReceiver(receiverId));
    }

    @GetMapping("{senderId}/sent")
    public ResponseEntity<List<Message>> getSentMessages(@PathVariable Integer senderId) {
        return ResponseEntity.ok(messageService.findMessageBySender(senderId));
    }

    @PostMapping("{senderId}/sendTo/{receiverId}")
    public ResponseEntity<Message> sendMessage(@PathVariable Integer senderId, @PathVariable Integer receiverId, @RequestBody Message message) {

      return ResponseEntity.ok(messageService.sendMessage(senderId, receiverId, message));
    }
}
