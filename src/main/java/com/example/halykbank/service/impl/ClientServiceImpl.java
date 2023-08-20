package com.example.halykbank.service.impl;

import com.example.halykbank.model.Client;
import com.example.halykbank.repository.ClientRepository;
import com.example.halykbank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client save(Client object) {
        return repository.save(object);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client update(Client object) {
        return repository.save(object);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id).get());
    }

    @Override
    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    public Client findClientByLogin(String login){
        return repository.findClientByLogin(login);
    }

}
