package com.experess.news.controller;

import com.experess.news.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("api")
public class ArticleAPI {

    private final ArticleService articleService;

    @Autowired
    public ArticleAPI(ArticleService articleService) {
        this.articleService = articleService;
    }


}
