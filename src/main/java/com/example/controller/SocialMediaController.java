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
import com.example.service.AccountService;
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

    @Autowired 
    private AccountService accountSer;

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {

        if(accountSer.existsByUser(account.getUsername()))
        {
            return ResponseEntity.status(409).build();
        }

        Account test = accountSer.insertAccount(account);

        if(test != null)
        {
            return ResponseEntity.ok(test);
        }

        return ResponseEntity.status(400).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account loginInfo)
    {
        boolean succes = accountSer.login(loginInfo.getUsername(), loginInfo.getPassword());
        if(succes)
        {
            Account returnAccount = accountRepo.findByUsername(loginInfo.getUsername());
            return ResponseEntity.ok(returnAccount);
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> creatMessage(@RequestBody Message message)
    {
        return messageSer.insertMessage(message);
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
        if(messageSer.deleteMessage(messageId))
        {
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage (@PathVariable int messageId, @RequestBody Message newMessage)
    {
        String test = newMessage.getMessageText();
        Message testMessage = null;

        return messageSer.updateMessage(messageId, test);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getmessageByAccountID(@PathVariable int accountId)
    {
        return ResponseEntity.ok(messageSer.getAllMessagesById(accountId));
    }
}
