package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String content;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;

    // getters and setters
}
