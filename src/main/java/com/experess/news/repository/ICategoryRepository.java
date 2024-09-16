package com.experess.news.repository;

import com.experess.news.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, String> {
}
