package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.iservice.IArticleService;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("/api/articles")

public class ArticleAPI {

    private final IArticleService articleService;

    @Autowired
    public ArticleAPI(IArticleService articleService) {
        this.articleService = articleService;
    }

    // GET /api/articles/{id}
    @GetMapping("/{id}")
    public ArticleResponse readArticle(@PathVariable String id) {
        return articleService.readArticle(id);
    }

    // POST /api/articles
    @PostMapping
    public Article writeArticle(@RequestBody ArticleRequest articleRequest) {
        return articleService.writeArticle(articleRequest);
    }

}
