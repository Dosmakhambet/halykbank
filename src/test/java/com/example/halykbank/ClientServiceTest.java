package com.example.halykbank;

import com.example.halykbank.model.Client;
import com.example.halykbank.model.Message;
import com.example.halykbank.repository.ClientRepository;
import com.example.halykbank.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl service;
    @Mock
    private ClientRepository repository;

    private Client getClient1(){
        return Client.builder()
                .id(1L)
                .login("test")
                .firstName("Test")
                .lastName("Testov")
                .messages(new ArrayList<>())
                .build();
    }

    private Client getClient2(){
        return Client.builder()
                .id(1L)
                .login("test2")
                .firstName("Test2")
                .lastName("Testov2")
                .messages(List.of(getMessage1(), getMessage2(), getMessage3()))
                .build();
    }

    private Message getMessage1(){
        return Message.builder()
                .id(1L)
                .bodyText("Text1")
                .build();
    }

    private Message getMessage2(){
        return Message.builder()
                .id(2L)
                .bodyText("Text2")
                .build();
    }
    private Message getMessage3(){
        return Message.builder()
                .id(3L)
                .bodyText("Text3")
                .build();
    }

    private List<Client> getClients(){
        return List.of(getClient1(),getClient2());
    }
    @Test
    public void get(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(getClient1()));

        Client client = service.findById(1L).get();

        assertEquals(1,client.getId().intValue());
        assertEquals("Test",client.getFirstName());
        assertEquals("Testov",client.getLastName());
    }

    @Test
    public void getAll() {
        when(repository.findAll()).thenReturn(getClients());

        List<Client> users = service.findAll();

        assertEquals(2, users.size());
        assertEquals(3, users.get(1).getMessages().size());

    }
}
