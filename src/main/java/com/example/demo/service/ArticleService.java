package com.example.demo.service;

import com.example.demo.iservice.IArticleService;
import com.example.demo.model.Response.ArticleResponse;
import com.example.demo.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleService implements IArticleService{

    public final IArticleRepository iArticleRepository;

    @Autowired
    public ArticleService(IArticleRepository iArticleRepository) {
        this.iArticleRepository = iArticleRepository;
    }

    @Override
    public ArticleResponse readArticle(String id) {
        return iArticleRepository.findById(id).
                map(ArticleResponse::new).orElseThrow(() ->
                new RuntimeException("Could not find article"));
    }
}
