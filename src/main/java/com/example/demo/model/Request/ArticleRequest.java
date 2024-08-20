package com.example.demo.model.Request;

import com.example.demo.entity.Type;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile file;
}
