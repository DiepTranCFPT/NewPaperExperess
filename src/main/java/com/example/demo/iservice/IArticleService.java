package com.example.demo.iservice;

import com.example.demo.entity.Article;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponse;

public interface IArticleService {
    ArticleResponse readArticle(String id);

    Article writeArticle(ArticleRequest articleRequest);

}
