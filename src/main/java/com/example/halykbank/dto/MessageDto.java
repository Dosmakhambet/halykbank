package com.example.halykbank.dto;


import com.example.halykbank.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String bodyText;
    private Date createdAt;
    private ClientDto client;

    public static MessageDto asDto(Message entity){
        return MessageDto.builder()
                .id(entity.getId())
                .bodyText(entity.getBodyText())
                .createdAt(entity.getCreatedAt())
                .client(ClientDto.asDto(entity.getClient()))
                .build();
    }

    public Message asEntity(){
        return Message.builder()
                .id(id)
                .bodyText(bodyText)
                .createdAt(createdAt)
                .client(client.asEntity())
                .build();
    }
}
