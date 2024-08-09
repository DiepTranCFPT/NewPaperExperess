package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String type;
    private String uploadedDate;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // getters and setters
}

