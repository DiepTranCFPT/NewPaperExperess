package com.example.demo.iservice;

import com.example.demo.model.Response.ArticleResponse;

public interface IArticleService {
    ArticleResponse readArticle(String id);

}
