package com.experess.news.repository;

import com.experess.news.entity.Type;
import com.experess.news.repository.base_repo.BaseRepository;

import java.util.Optional;

public interface ITypeRepository extends BaseRepository<Type, String> {
    Optional<Type> findByTypeNameContaining(String typeName);
}
