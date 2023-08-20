package com.example.halykbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String bodyText;
    @Column
    @CreatedDate
    private Date createdAt;
    @ManyToOne
    private Client client;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", bodyText='" + bodyText + '\'' +
                ", createdAt=" + createdAt +
                ", client=" + client +
                '}';
    }
}

