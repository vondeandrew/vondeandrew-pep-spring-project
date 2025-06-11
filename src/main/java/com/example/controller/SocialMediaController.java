package com.example.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.MessageService;

import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.joran.spi.DefaultClass;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private MessageService messageSer;

    @GetMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        String testUser = account.getUsername();
        String testPass = account.getPassword();
        if(testUser == null || testUser.isBlank() || testPass == null || testPass.isBlank())
        {
            return ResponseEntity.status(400).build();
        }

        if(accountRepo.findByUsername(testUser) != null)
        {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(accountRepo.save(account));
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account loginInfo)
    {
        Account exists = accountRepo.findByUsername(loginInfo.getUsername());
        if(exists != null && exists.getPassword().equals(loginInfo.getPassword()))
        {
            return ResponseEntity.ok(exists);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> creatMessage(@RequestBody Message message)
    {
        String test = message.getMessageText();
        if(test == null || test.isBlank()
    || test.length() > 255 || !accountRepo.existsById(message.getPostedBy()))
    {
        return ResponseEntity.status(400).build();
    }
        return ResponseEntity.ok(messageRepo.save(message));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageSer.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getmessageByID(@PathVariable int messageId)
    {
        return ResponseEntity.ok(messageSer.getmessageByID(messageId));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId)
    {
        if(!messageSer.deleteMessage(messageId))
            return ResponseEntity.status(200).build();

        //messageRepo.deleteById(messageId);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage (@PathVariable int messageId, @RequestBody Message newMessage)
    {
        String test = newMessage.getMessageText();
        Message testMessage = null;

        if(test == null || test.isBlank() || test.length() > 255) {
            return ResponseEntity.status(400).build();
        }

        return messageRepo.findById(messageId).map(exists -> {
            exists.setMessageText(newMessage.getMessageText());
            messageRepo.save(exists);
            return ResponseEntity.ok(1);
        }).orElse(ResponseEntity.status(400).build());
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getmessageByAccountID(@PathVariable int accountId)
    {
        return ResponseEntity.ok(messageRepo.findByPostedBy(accountId));
    }
}
