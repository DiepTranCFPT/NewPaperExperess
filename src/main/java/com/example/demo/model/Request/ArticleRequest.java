package com.example.demo.model.Request;

import lombok.Data;

@Data
public class ArticleRequest {
    private String title;
    private String content;
    private String status;
    private String publishedDate;
    private String createdDate;
    private String author_id;

}
