package com.experess.news.repository;

import com.experess.news.entity.User;

import com.experess.news.infor.Role;
import com.experess.news.repository.base_repo.BaseRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface AuthenticationRepository extends BaseRepository<User, String>
{
     Optional<User> findByEmail(String email);
     boolean existsByEmail(String email);
     boolean existsByUid(String uid);
     User findByName(String name);
     List<User> findByNameContainingAndRole(@NotNull String name, @NotNull Role role);

}
