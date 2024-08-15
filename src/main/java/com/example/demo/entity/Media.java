package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    private byte[] data;

    private String type;
    private String uploadedDate;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // getters and setters
}

