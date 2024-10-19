package com.experess.news.repository;

import com.experess.news.entity.Report;
import com.experess.news.repository.base_repo.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportRepository extends BaseRepository<Report, String> {
}
