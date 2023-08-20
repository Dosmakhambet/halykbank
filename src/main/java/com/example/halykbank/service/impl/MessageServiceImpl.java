package com.example.halykbank.service.impl;

import com.example.halykbank.model.Message;
import com.example.halykbank.repository.MessageRepository;
import com.example.halykbank.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message save(Message object) {
        return repository.save(object);
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message update(Message object) {
        return repository.save(object);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id).get());
    }

    @Override
    public Optional<Message> findById(Long id) {
        return repository.findById(id);
    }
}
