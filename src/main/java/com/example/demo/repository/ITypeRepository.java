package com.example.demo.repository;

import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepository extends JpaRepository<Type, String> {
}
