package com.example.demo.service;

import com.example.demo.entity.Rating;
import com.example.demo.iservice.IArticleService;
import com.example.demo.iservice.IAuthenticationService;
import com.example.demo.iservice.IRatingService;
import com.example.demo.model.Request.RatingRequest;
import com.example.demo.repository.IRatingRepository;
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
