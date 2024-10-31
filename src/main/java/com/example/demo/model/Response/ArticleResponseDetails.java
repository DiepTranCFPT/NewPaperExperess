package com.example.demo.model.Response;


import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Media;
import com.example.demo.entity.Type;
import com.example.demo.utils.OtherFunctions;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * su dung khi xem bai viet
 */
@Data
public class ArticleResponseDetails {
    private String id;
    private String title;
    private String content;
    private String status;
    private LocalDateTime createdDate;
    private String titlePhoto;
    private String author;
    private Type type;
    private List<Comment> comments;
    private List<Media> mediaList;
    private int ratings;
    private int access;



    public ArticleResponseDetails(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.status = article.getStatus();
        this.createdDate = article.getTimeCreated();
        this.author = String.valueOf(article.getAuthor().getId());
        this.type = article.getType();
        this.comments = article.getComments();
        this.mediaList = article.getMediaList();
        this.ratings = OtherFunctions.ratingMedium(article.getRatings());
        this.access = article.getAccess();
    }

}

