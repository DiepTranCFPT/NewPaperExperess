package com.experess.news.repository;

import com.experess.news.entity.Article;
import com.experess.news.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IArticleRepository extends JpaRepository<Article,String> {
    List<Article> findByType(Type type);
    List<Article> findByAuthor_NameContaining(@NotNull String author);
    List<Article> findByContentContaining(@NotNull String content);
    List<Article> findAllByPublishedDate(@NotNull String publishedDate);
    List<Article> findByTitleContaining(@NotNull String title);
}