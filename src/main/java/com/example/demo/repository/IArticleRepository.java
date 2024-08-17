package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article,String> {
}
