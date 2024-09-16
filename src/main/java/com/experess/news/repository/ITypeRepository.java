package com.experess.news.repository;

import com.experess.news.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITypeRepository extends JpaRepository<Type, String> {
    Optional<Type> findByTypeNameContaining(String typeName);
}
