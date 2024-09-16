package com.experess.news.repository;

import com.experess.news.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,String> {
}
