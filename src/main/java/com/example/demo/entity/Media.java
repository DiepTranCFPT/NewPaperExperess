package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String url;
    private String type;
    private String uploadedDate;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // getters and setters
}

