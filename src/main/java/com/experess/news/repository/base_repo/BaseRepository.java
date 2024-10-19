package com.experess.news.repository.base_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, id> extends JpaRepository<T, id> {
}
