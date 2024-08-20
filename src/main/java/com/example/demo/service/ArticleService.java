package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.iservice.IArticleService;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponse;
import com.example.demo.repository.AuthenticationRepository;
import com.example.demo.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ArticleService implements IArticleService {

    public final IArticleRepository iArticleRepository;
    public final AuthenticationRepository authenticationRepository;

    @Autowired
    public ArticleService(IArticleRepository iArticleRepository
            , AuthenticationRepository authenticationRepository) {
        this.iArticleRepository = iArticleRepository;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public ArticleResponse readArticle(String id) {
        return iArticleRepository.findById(id).
                map(ArticleResponse::new).orElseThrow(() ->
                        new RuntimeException("Could not find article"));
    }

    @Override
    @Transactional
    public Article writeArticle(ArticleRequest articleRequest) {
        User user = authenticationRepository.findById(articleRequest.getAuthor_id())
                .orElseThrow(() -> new RuntimeException("Not Login"));
        Article article = Article.builder()
                .author(user)
                .title(articleRequest.getTitle())
                .type(articleRequest.getType())
                .content(articleRequest.getContent())
                .status(articleRequest.getStatus())
                .publishedDate(articleRequest.getPublishedDate())
                .titlePhoto(articleRequest.getTitlePhoto())
                .build();
        return iArticleRepository.save(article);
    }



}
