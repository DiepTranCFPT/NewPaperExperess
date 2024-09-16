package com.experess.news.repository;

import com.experess.news.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Rating, String> {
}
