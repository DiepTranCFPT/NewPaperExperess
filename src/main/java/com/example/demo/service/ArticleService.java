package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.iService.IArticleService;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponseDetails;
import com.example.demo.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Override
    public ArticleResponseDetails readArticle(String id) {
        return null;
    }

    @Override
    public Article writeArticle(ArticleRequest articleRequest) {
        return null;
    }

    @Override
    public Article editArticle(String id, ArticleRequest articleRequest) {
        return null;
    }

    @Override
    public List<String> filterByType(String typename) {
        return List.of();
    }

    @Override
    public List<String> searchByKey(String typename) {
        return List.of();
    }

    @Override
    public Optional<Article> findById(String id) {
        return Optional.empty();
    }
}
