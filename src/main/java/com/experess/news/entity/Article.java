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

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String status;

    private boolean isPublished; // true means published, false means draft or hidden

    @ManyToOne
    @NotNull
    private User author;

    @OneToOne
    private Type type; // Assuming Type is optional, otherwise use @NotNull

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    private int access; // tracks the number of views or access count
}
