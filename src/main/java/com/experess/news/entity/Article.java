package com.experess.news.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Article extends BaseObject {

    private String title;
    private String content;
    private String status;

    private boolean isShow;// true la dang f an

    @ManyToOne
    @NotNull
    private User author;

    @OneToOne
    private Type type;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Media> mediaList;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Report> reports;

    private int access; // tinh luoc tuy cap vao bai viet

}

