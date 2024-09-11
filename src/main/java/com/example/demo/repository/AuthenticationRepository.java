package com.example.demo.repository;

import com.example.demo.entity.User;

import com.example.demo.infor.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AuthenticationRepository extends JpaRepository<User, String>
{   // dua ra daatabase

     Optional<User> findByEmail(String email);
     boolean existsByEmail(String email);
     boolean existsByUid(String uid);
     User findByName(String name);
     List<User> findByNameContainingAndRole(@NotNull String name, @NotNull Role role);

}
