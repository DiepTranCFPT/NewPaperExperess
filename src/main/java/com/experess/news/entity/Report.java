package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Report extends BaseObject {

    private String Content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

}
