package com.experess.news.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
