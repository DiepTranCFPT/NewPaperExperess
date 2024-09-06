package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("api")
public class ContentControler {

    private final ArticleService articleService;

    @Autowired
    public ContentControler(ArticleService articleService) {
        this.articleService = articleService;
    }

}
