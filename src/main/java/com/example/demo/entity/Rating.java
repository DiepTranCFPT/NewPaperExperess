package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String status;
    private String createdDate;
    private int rating;
    private boolean isPeerReview;
    private String comments;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;
}

