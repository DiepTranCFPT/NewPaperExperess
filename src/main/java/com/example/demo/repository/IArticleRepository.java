package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IArticleRepository extends JpaRepository<Article,String> {
    List<Article> findByType(Type type);
    List<Article> findByAuthor_NameContaining(@NotNull String author);
    List<Article> findByContentContaining(@NotNull String content);
}