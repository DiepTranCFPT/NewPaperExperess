package com.experess.news.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {


    private String createdDate;
    private int rating;
    private boolean isPeerReview; // co phai danh gia ngang hang k?

    private String articleId;

    private String userId;
}
