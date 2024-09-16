package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String createdDate;
    private int rating;
    private boolean isPeerReview; // co phai danh gia ngang hang k?

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;
}

