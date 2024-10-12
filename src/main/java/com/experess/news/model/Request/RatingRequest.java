package com.experess.news.model.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


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
