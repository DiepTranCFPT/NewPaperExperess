package com.example.demo.iservice;

import com.example.demo.entity.Article;
import com.example.demo.entity.Type;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponse;

import java.util.List;
import java.util.function.Function;

public interface IArticleService {

    ArticleResponse readArticle(String id);

    Article writeArticle(ArticleRequest articleRequest);

    Article editArticle(String id,ArticleRequest articleRequest);

    List<Article> filterByType(String typename);
}
