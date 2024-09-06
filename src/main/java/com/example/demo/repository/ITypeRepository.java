package com.example.demo.repository;

import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITypeRepository extends JpaRepository<Type, String> {
    Optional<Type> findByTypeNameContaining(String typeName);
}
