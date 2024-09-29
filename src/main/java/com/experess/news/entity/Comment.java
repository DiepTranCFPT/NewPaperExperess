package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseObject {

    private String content;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;

    // getters and setters
}
