package com.example.demo.model.Response;

import com.example.demo.entity.*;
import lombok.Data;

import java.util.List;


@Data
public class ArticleResponse {
    private String id;
    private String title;
    private String content;
    private String status;
    private String publishedDate;
    private String createdDate;
    private String titlePhoto;
    private String author;
    private Type type;
    private List<Comment> comments;
    private List<Media> mediaList;
    private int ratings;


    public ArticleResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.status = article.getStatus();
        this.publishedDate = article.getPublishedDate();
        this.createdDate = article.getCreatedDate();
        this.titlePhoto = article.getTitlePhoto();
        this.author = article.getAuthor().getId();
        this.type = article.getType();
        this.comments = article.getComments();
        this.mediaList = article.getMediaList();
        this.ratings = rating(article.getRatings());
    }

    public static int rating(List<Rating> articles){
        int rating = 0;
        for (Rating r : articles){
            rating += r.getRating();
        }
        return rating / articles.size();
    }

}

