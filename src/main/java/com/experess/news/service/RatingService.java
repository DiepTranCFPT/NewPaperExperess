package com.experess.news.service;

import com.experess.news.entity.Article;
import com.experess.news.entity.Rating;
import com.experess.news.entity.User;
import com.experess.news.iservice.IArticleService;
import com.experess.news.iservice.IRatingService;
import com.experess.news.model.Request.RatingRequest;
import com.experess.news.repository.AuthenticationRepository;
import com.experess.news.repository.IRatingRepository;
import com.experess.news.repository.base_repo.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RatingService extends BaseService<Rating,String> implements IRatingService {

    private final IRatingRepository iRatingRepository;
    private final IArticleService articleService;
    private final AuthenticationRepository iAuthenticationService;

    @Autowired
    public RatingService(IRatingRepository iRatingRepository,
                         IArticleService articleService,
                         AuthenticationRepository iAuthenticationService
                         ) {
        super(iRatingRepository);
        this.iRatingRepository = iRatingRepository;
        this.articleService = articleService;
        this.iAuthenticationService = iAuthenticationService;
    }

    @Override
    public Rating createRating(RatingRequest rating, Function<String, User> userStringFunction, Function<String, Article> article) {
        return IRatingService.super.createRating(rating, userStringFunction, article);
    }

    @Override
    public Rating saveRating(RatingRequest rating) {
        return null;
    }


//    @Override
//    public Rating saveRating(RatingRequest rating) {
//       return iRatingRepository.save(createRating(rating,
//               iAuthenticationService::findById,
//               articleService::findById));
//    }





}
