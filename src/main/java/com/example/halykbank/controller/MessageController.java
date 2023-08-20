package com.example.halykbank.controller;

import com.example.halykbank.dto.MessageDto;
import com.example.halykbank.dto.RequestDto;
import com.example.halykbank.model.Message;
import com.example.halykbank.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@RestController
public class MessageController {
    @Autowired
    private final MessageService messageService;

    @Autowired
    private BlockingQueue<RequestDto> messageQueue;

    public MessageController(MessageService MessageService) {
        this.messageService = MessageService;
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(MessageDto.asDto(messageService.findById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> findAll(){

        return new ResponseEntity<>(messageService.findAll().stream().map(MessageDto::asDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/messages")
    public void save(@RequestBody RequestDto dto){
        messageQueue.add(dto);
    }

    @DeleteMapping("/messages/{id}")
    void delete(@PathVariable("id") Long id){
        messageService.delete(id);
    }
}
