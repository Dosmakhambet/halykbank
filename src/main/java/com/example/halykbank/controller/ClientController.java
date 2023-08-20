package com.example.halykbank.controller;

import com.example.halykbank.dto.ClientDto;
import com.example.halykbank.model.Client;
import com.example.halykbank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {
    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(ClientDto.asDto(clientService.findById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> findAll(){

        return new ResponseEntity<>(clientService.findAll().stream().map(ClientDto::asDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto){
        return new ResponseEntity<>(ClientDto.asDto(clientService.save(dto.asEntity())), HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    void delete(@PathVariable("id") Long id){
        clientService.delete(id);
    }
}
