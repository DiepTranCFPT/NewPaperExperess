package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.infor.Role;
import com.example.demo.iservice.IArticleService;
import com.example.demo.iservice.ITypeService;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponse;
import com.example.demo.repository.AuthenticationRepository;
import com.example.demo.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class ArticleService implements IArticleService {

    private final IArticleRepository iArticleRepository;
    private final AuthenticationRepository authenticationRepository;
    private final ITypeService iTypeService;

    @Autowired
    public ArticleService(IArticleRepository iArticleRepository
            , AuthenticationRepository authenticationRepository,
                          ITypeService iTypeService) {
        this.iArticleRepository = iArticleRepository;
        this.authenticationRepository = authenticationRepository;
        this.iTypeService = iTypeService;
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

    @Override
    public Article editArticle(String id, ArticleRequest articleRequest) {
        Article article = iArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find article'"));
        Article updatedArticle = Article.builder()
                .id(article.getId()) //**
                .title(articleRequest.getTitle())
                .type(articleRequest.getType())
                .content(articleRequest.getContent())
                .status(articleRequest.getStatus())
                .publishedDate(articleRequest.getPublishedDate())
                .titlePhoto(articleRequest.getTitlePhoto())
                .build();

        return iArticleRepository.save(updatedArticle);
    }




    @Override
    public List<String> filterByType(String typename) {
        return List.copyOf(iArticleRepository.
                findByType(iTypeService.getType(typename))
                .stream().map(Article::getId)
                .toList());
    }

    @Override
    public List<String> searchByKey(String typename) {
        List<User> users = authenticationRepository.findByNameContainingAndRole(typename, Role.AUTHOR);
        return Filtration.getListString(users);
    }

}
