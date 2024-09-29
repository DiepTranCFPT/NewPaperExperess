package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Comment extends BaseObject {

    private String content;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;

    // getters and setters
}
