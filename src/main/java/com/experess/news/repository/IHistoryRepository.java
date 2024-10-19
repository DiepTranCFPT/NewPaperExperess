package com.experess.news.repository;

import com.experess.news.entity.History;
import com.experess.news.repository.base_repo.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryRepository extends BaseRepository<History,String> {
}
