package com.example.halykbank.dto;


import com.example.halykbank.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private Date createdAt;
    private Date updatedAt;
    public static ClientDto asDto(Client entity){
        return ClientDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .middleName(entity.getMiddleName())
                .birthday(entity.getBirthday())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public Client asEntity(){
        return Client.builder()
                .id(id)
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .birthday(birthday)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
