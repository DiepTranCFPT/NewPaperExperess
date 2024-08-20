package com.example.demo.model.Request;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Media;
import com.example.demo.entity.Type;
import lombok.Data;

import java.util.List;

@Data
public class ArticleRequest {
    private String title;
    private String content;
    private String status;
    private String publishedDate;
    private String createdDate;
    private String titlePhoto;
    private String author_id;
    private Type type;
}
