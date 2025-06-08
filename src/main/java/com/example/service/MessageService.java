package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    public Message getmessageByID(int id)
    {
        return null;
    }

    public List<Message> getAllMessagesById(int id)
    {
        return null;
    }

    public Message updateMessage(int id, String newMessage)
    {
        return null;
    }

    public Message deleteMessage(int id)
    {
        return null;
    }
}
