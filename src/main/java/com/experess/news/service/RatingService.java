package com.experess.news.service;

import com.experess.news.entity.Rating;
import com.experess.news.iservice.IArticleService;
import com.experess.news.iservice.IAuthenticationService;
import com.experess.news.iservice.IRatingService;
import com.experess.news.model.Request.RatingRequest;
import com.experess.news.repository.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService {

    private final IRatingRepository iRatingRepository;
    private final IArticleService articleService;
    private final IAuthenticationService authenticationService;

    @Autowired
    public RatingService(IRatingRepository iRatingRepository,
                         IArticleService articleService,
                         IAuthenticationService authenticationService
                         ) {
        this.iRatingRepository = iRatingRepository;
        this.articleService = articleService;
        this.authenticationService = authenticationService;
    }


    @Override
    public Rating saveRating(RatingRequest rating) {
       return iRatingRepository.save(createRating(rating,
               authenticationService::findById,
               articleService::findById));
    }





}
