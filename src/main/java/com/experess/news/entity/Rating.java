package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating extends BaseObject {

    private int rating;
    private boolean isPeerReview; // co phai danh gia ngang hang k?

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;
}

