package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepo;

    public Message insertMessage(Message newMessage)
    {
        return null;
    }

    public ResponseEntity<List<Message>> getAllMessages()
    {
        return ResponseEntity.ok(messageRepo.findAll());
    }

    public ResponseEntity<Message> getmessageByID(@PathVariable Integer checkId)
    {
        return ResponseEntity.ok(messageRepo.findById(checkId).orElse(null));
    }

    public ResponseEntity<List<Message>> getAllMessagesById(@PathVariable Integer checkId)
    {
        List<Message> messages = messageRepo.findByPostedBy(checkId);
        return ResponseEntity.ok(messages);
    }

    public Message updateMessage(int id, String newMessage)
    {
        return null;
    }

    public ResponseEntity<Object> deleteMessage(@PathVariable Integer checkId)
    {
        boolean isThere = messageRepo.existsById(checkId);

        if(isThere)
        {
            messageRepo.deleteById(checkId);
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.ok().build();
    }
}
