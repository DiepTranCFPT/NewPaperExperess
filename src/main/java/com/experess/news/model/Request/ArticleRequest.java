package com.experess.news.model.Request;

import com.experess.news.entity.Type;
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
