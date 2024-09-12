package com.example.demo.controller;

import com.example.demo.iservice.IArticleService;
import com.example.demo.service.ArticleService;
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
