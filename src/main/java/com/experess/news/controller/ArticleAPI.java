package com.experess.news.controller;

import com.experess.news.iservice.IArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("api")
public class ArticleAPI {

    private final IArticleService articleService;

    @Autowired
    public ArticleAPI(IArticleService articleService) {
        this.articleService = articleService;
    }


}
