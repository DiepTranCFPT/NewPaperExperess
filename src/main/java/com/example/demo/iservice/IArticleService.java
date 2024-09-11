package com.example.demo.iservice;

import com.example.demo.entity.Article;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponse;

import java.util.List;

public interface IArticleService {

    ArticleResponse readArticle(String id);

    Article writeArticle(ArticleRequest articleRequest);

    Article editArticle(String id,ArticleRequest articleRequest);

    List<String> filterByType(String typename);

    /**
     * Search for Key (content, author, date, type)
     * @param typename
     * @return list ID
     */
    List<String> searchByKey (String typename);
}
