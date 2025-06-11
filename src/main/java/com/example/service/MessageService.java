package com.example.service;

import java.util.List;
import java.util.Optional;

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

    public Message updateMessage(int id, String newMessage)
    {
        Optional<Message> opMess = messageRepo.findById(id);
        if(opMess.isPresent())
        {
            Message message = opMess.get();
            message.setMessageText(newMessage);
            return messageRepo.save(message);
        }
        return null;
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
