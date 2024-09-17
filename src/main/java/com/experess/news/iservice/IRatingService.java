package com.experess.news.iservice;

import com.experess.news.entity.Article;
import com.experess.news.entity.Rating;
import com.experess.news.entity.User;
import com.experess.news.infor.Role;
import com.experess.news.model.Request.RatingRequest;

import java.util.function.Function;

public interface IRatingService {


    /**
     * @param rating             {@link RatingRequest} voi cac gia tri phu hop nhan vao
     * @param userStringFunction {@link Function<String,User>} nhan vao 1 {@link String} la 1 id va tra ve {@link User}
     * @param article            {@link Article} vao 1 {@link String} la 1 id va tra ve {@link Article}
     * @return {@link Rating}
     */

    default Rating createRating(RatingRequest rating,
                                Function<String, User> userStringFunction,
                                Function<String, Article> article) {

        User user = userStringFunction.apply(rating.getUserId());
        Article article1 = article.apply(rating.getArticleId());

        if (article1 == null) throw new RuntimeException("Article not found");
        if (user == null) throw new RuntimeException("User not found");

        return Rating.builder().rating(rating.getRating())
                .createdDate(rating.getCreatedDate())
                .user(user)
                .article(article1)
                .isPeerReview(user.getRole().equals(Role.AUTHOR))
                .build();
    }

    Rating saveRating(RatingRequest rating);
}
