package com.example.demo.repository;

import com.example.demo.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowRepository extends JpaRepository<Follows,String> {
}
