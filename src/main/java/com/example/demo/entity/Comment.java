package com.example.demo.entity;

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
    private Account user;

    // getters and setters
}
