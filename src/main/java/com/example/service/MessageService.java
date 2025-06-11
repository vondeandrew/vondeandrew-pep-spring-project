package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private AccountRepository accountRepo;

    public ResponseEntity<Message> insertMessage(Message message)
    {
        String test = message.getMessageText();
        if(test == null || test.isBlank()
    || test.length() > 255 || !accountRepo.existsById(message.getPostedBy()))
    {
        return ResponseEntity.status(400).build();
    }
        return ResponseEntity.ok(messageRepo.save(message));
    }

    public List<Message> getAllMessages()
    {
        return messageRepo.findAll();
    }

    public Message getmessageByID(@PathVariable Integer checkId)
    {
        return messageRepo.findById(checkId).orElse(null);
    }

    public List<Message> getAllMessagesById(@PathVariable Integer checkId)
    {
        List<Message> messages = messageRepo.findByPostedBy(checkId);
        return messages;
    }

    public ResponseEntity<Integer> updateMessage(int id, String newMessage)
    {
        String test = newMessage;
        Message testMessage = null;

        if(test == null || test.isBlank() || test.length() > 255) {
            return ResponseEntity.status(400).build();
        }

        return messageRepo.findById(id).map(exists -> {
            exists.setMessageText(newMessage);
            messageRepo.save(exists);
            return ResponseEntity.ok(1);
        }).orElse(ResponseEntity.status(400).build());
    }

    public boolean deleteMessage(@PathVariable Integer checkId)
    {
        boolean isThere = messageRepo.existsById(checkId);

        if(isThere)
        {
            messageRepo.deleteById(checkId);
            return true;
        }
        return false;
    }
}
