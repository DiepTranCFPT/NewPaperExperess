package com.experess.news.repository;

import com.experess.news.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMediaRepository extends JpaRepository<Media, String> {
}
