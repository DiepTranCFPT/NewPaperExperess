package com.experess.news.service;

import com.experess.news.entity.Article;
import com.experess.news.entity.User;
import com.experess.news.iservice.IArticleService;
import com.experess.news.iservice.ITypeService;
import com.experess.news.model.Request.ArticleRequest;
import com.experess.news.model.Response.articlereponse.ArticleResponseDetails;
import com.experess.news.repository.AuthenticationRepository;
import com.experess.news.repository.ICareRepository;
import com.experess.news.repository.IHistoryRepository;
import com.experess.news.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ArticleService implements IArticleService {

    private final IArticleRepository iArticleRepository;
    private final AuthenticationRepository authenticationRepository;
    private final ITypeService iTypeService;
    private final ICareRepository careRepository;
    private final IHistoryRepository historyRepository;

    @Autowired
    public ArticleService(IArticleRepository iArticleRepository
            , AuthenticationRepository authenticationRepository,
                          ITypeService iTypeService,
                          ICareRepository careRepository,
                          IHistoryRepository historyRepository
                          ) {
        this.iArticleRepository = iArticleRepository;
        this.authenticationRepository = authenticationRepository;
        this.iTypeService = iTypeService;
        this.careRepository = careRepository;
        this.historyRepository = historyRepository;
    }


    public void Access(Article article) {
        int count = setAccess(article.getAccess());
        article.setAccess(count);
        iArticleRepository.save(article);
    }

    @Override
    public ArticleResponseDetails readArticle(String id) {
        Article article = iArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find article"));

        if (article != null) {
            Access(article);
            return new ArticleResponseDetails(article);
        }
        throw new RuntimeException("Could not find article");
    }

    @Override
    @Transactional
    public Article writeArticle(ArticleRequest articleRequest) {
        User user = authenticationRepository.findById(articleRequest.getAuthor_id())
                .orElseThrow(() -> new RuntimeException("Not Login"));
        Article article = Article.builder()///  loi khi gọi các field từ class choa
                .author(user)
                .title(articleRequest.getTitle())
                .type(articleRequest.getType())
                .content(articleRequest.getContent())
                .status(articleRequest.getStatus())
                .build();
        return iArticleRepository.save(article);
    }

    @Override
    public Article editArticle(String id, ArticleRequest articleRequest) {

        Article article = iArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find article'"));


        var updatedArticle = Article.builder()
                .id(id)
                .title(articleRequest.getTitle())
                .type(articleRequest.getType())
                .content(articleRequest.getContent())
                .status(articleRequest.getStatus())
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
//        List<User> users = authenticationRepository.findByNameContainingAndRole(typename, Role.AUTHOR);
//        return Filtration.getListString(users);
        return null;
    }

    @Override
    public Article findById(String id) {
        return iArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not found"));
    }






}
