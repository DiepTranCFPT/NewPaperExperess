package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String status;
    private String createdDate;
    private int rating;
    private boolean isPeerReview;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

