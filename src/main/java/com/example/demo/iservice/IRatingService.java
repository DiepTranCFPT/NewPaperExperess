package com.example.demo.iservice;

import com.example.demo.entity.Article;
import com.example.demo.entity.Rating;
import com.example.demo.entity.User;
import com.example.demo.model.Request.RatingRequest;

import java.util.function.Function;

public interface IRatingService {


    default Rating createRating(RatingRequest rating,
                                Function<String, User> userStringFunction,
                                Function<String, Article> article) {
        return Rating.builder().rating(rating.getRating())
                .createdDate(rating.getCreatedDate())
                .user(userStringFunction.apply(rating.getUserId()))
                .article(article.apply(rating.getArticleId()))
                .isPeerReview(rating.isPeerReview())
                .build();
    }

    Rating saveRating(RatingRequest rating);
}

