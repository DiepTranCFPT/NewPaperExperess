package com.experess.news.model.Request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RatingRequest {

    private String createdDate;

    @Min(1)
    @Max(5)
    private int rating;

    private boolean isPeerReview; // co phai danh gia ngang hang k?

    @NotNull
    private String articleId;

    @NotNull
    private String userId;
}
