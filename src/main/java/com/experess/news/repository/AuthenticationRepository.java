package com.experess.news.repository;

import com.experess.news.entity.User;

import com.experess.news.infor.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;



public interface AuthenticationRepository extends JpaRepository<User, String>
{
     Optional<User> findByEmail(String email);
     boolean existsByEmail(String email);
     boolean existsByUid(String uid);
     User findByName(String name);
     List<User> findByNameContainingAndRole(@NotNull String name, @NotNull Role role);

}
