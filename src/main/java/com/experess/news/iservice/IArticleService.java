package com.experess.news.iservice;

import com.experess.news.entity.Article;
import com.experess.news.model.Request.ArticleRequest;
import com.experess.news.model.Response.ArticleResponse;

import java.util.List;

public interface IArticleService {

    ArticleResponse readArticle(String id);

    Article writeArticle(ArticleRequest articleRequest);

    Article editArticle(String id,ArticleRequest articleRequest);

    List<String> filterByType(String typename);

    /**
     * Search for Key (content, author, type)
     * @param typename
     * @return list ID
     */
    List<String> searchByKey (String typename);

    Article findById(String id);


    /**
     * so luong truy cap +1 neu nguoi dung xem bai viet
     * @param access {@link Integer access for Article}
     * @return {@link Integer access}
     */
    default int setAccess(int access){
        return ++access;
    }

}
