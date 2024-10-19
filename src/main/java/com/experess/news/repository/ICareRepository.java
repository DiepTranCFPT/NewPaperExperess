package com.experess.news.repository;

import com.experess.news.entity.Care;
import com.experess.news.repository.base_repo.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICareRepository extends BaseRepository<Care,String> {
}
