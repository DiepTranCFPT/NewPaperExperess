package com.experess.news.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
// kinh nghiem
public class Experience extends BaseObject {

    @ManyToOne
    private User user;

    private String tagExperience;

    @Min(1)
    @Max(5)
    private int level;
}
