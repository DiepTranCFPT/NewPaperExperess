package com.experess.news.repository;

import com.experess.news.entity.Article;
import com.experess.news.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IArticleRepository extends JpaRepository<Article, String> {
    List<Article> findByType(Type type);

    List<Article> findByAuthor_NameContaining(@NotNull String author);

    List<Article> findByContentContaining(@NotNull String content);

    List<Article> findAllByTimeCreated(@NotNull LocalDateTime timeCreated);

    List<Article> findByTitleContaining(@NotNull String title);

//    List<Article> findByRatingsBetween(int min,
//                                       int max);

//
//    @Query(value = "select MAX(a.access) from Article a") // tim access lơn nhat trong database
//    Integer maxAccess();
//
//    @Query("SELECT a FROM Article a WHERE a.access = :maxAccess")
//    List<Article> findAllByAccess(@Param("maxAccess") Integer maxAccess);



}