package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IArticleRepository extends JpaRepository<Article,String> {

    List<Article> findByType(Type type);
}
