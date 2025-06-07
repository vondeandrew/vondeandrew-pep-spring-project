package com.example.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import ch.qos.logback.core.joran.spi.DefaultClass;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @GetMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {

    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account loginInfo)
    {

    }

    @PostMapping("/messages")
    public ResponseEntity<Message> creatMessage(@RequestBody Message message)
    {

    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {

    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getmessageByID(@PathVariable int messageId)
    {

    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Message> deleteMessage(@PathVariable int messageId)
    {

    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Message> updateMessage (@PathVariable int messageId, @RequestBody Message newMessage)
    {

    }

    @getMapping("/accounts/{accountIs}/messages")
    public
}
