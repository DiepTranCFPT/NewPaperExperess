package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String content;
    private String status;
    private String publishedDate;
    private String createdDate;
    private String titlePhoto;

    @ManyToOne
    private User author;

    @OneToOne
    private Type type;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Media> mediaList;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Rating> ratings;

}

