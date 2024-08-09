package com.example.demo.repository;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AuthenticationRepository extends JpaRepository<User, Long>
{   // dua ra daatabase
    User findByEmail(String email);


    User findByVerificationCode(String code);

}
