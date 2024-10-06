package com.experess.news.repository;

import com.experess.news.entity.Care;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICareRepository extends JpaRepository<Care,String> {
}
