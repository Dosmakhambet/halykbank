package com.example.halykbank.service;

import com.example.halykbank.model.Client;

public interface ClientService extends GeneralService<Client,Long>{
    Client findClientByLogin(String login);
}
