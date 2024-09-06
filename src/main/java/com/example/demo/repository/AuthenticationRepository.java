package com.example.demo.repository;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface AuthenticationRepository extends JpaRepository<User, String>
{   // dua ra daatabase

     Optional<User> findByEmail(String email);
     boolean existsByEmail(String email);
     boolean existsByUid(String uid);
     User findByName(String name);




}
