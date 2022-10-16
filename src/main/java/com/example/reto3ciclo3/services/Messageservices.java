package com.example.reto3ciclo3.services;

import com.example.reto3ciclo3.Model.Message;
import com.example.reto3ciclo3.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Messageservices {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return (List<Message>) messageRepository.getAll();
    }

    public Optional<Message> getProduct(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message p) {
        if (p.getIdMessage() == null) {
            return messageRepository.save(p);
        } else {
            Optional<Message> e = messageRepository.getMessage(p.getIdMessage());
            if (e.isPresent()) {
                return p;
            } else {
                return messageRepository.save(p);
            }
        }
    }

    public Message update(Message messageModel) {
        if (messageModel.getIdMessage() != null) {
            Optional<Message> message = messageRepository.getMessage(messageModel.getIdMessage());
            if (!message.isEmpty()) {
                if (messageModel.getMessageText() != null) {
                    message.get().setMessageText(messageModel.getMessageText());
                }
                if (messageModel.getCostume() != null) {
                    message.get().setCostume(messageModel.getCostume());
                }
                if (messageModel.getClient() != null) {
                    message.get().setClient(messageModel.getClient());
                }
                messageRepository.save(message.get());
                return message.get();
            } else {
                return messageModel;
            }
        } else {
            return messageModel;
        }

    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Message> p = messageRepository.getMessage(id);
        if (p.isPresent()) {
            messageRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }

}
